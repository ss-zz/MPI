package com.sinosoft.mpi.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.dao.IPersonIndexDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.service.IPersonIndexService;

import net.sf.json.JSONArray;

/**
 * 主索引操作
 * @author admin
 *
 */
@Controller
@RequestMapping("/index/index.ac")
public class IndexOperate {
	
	private Logger logger = Logger.getLogger(IndexQueryController.class);	
	
	@Resource
	private IPersonIndexService personIndexService;
	
	@Resource
	private IPersonIdxLogService personIdxLogService;
	@Resource
	private IPersonIndexDao personIndexDao;
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 人工合并主主索引
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=merge")
	public String mergeIndex(String retiredPk,String survivingPk){
		try {
			personIndexService.mergeIndex(retiredPk, survivingPk);
		}
		catch (ValidationException e) {// 验证异常
			logger.error("合并用户时发生错误", e);
		}
		catch (Exception e) {
			logger.error("系统错误,无法完成合并主索引操作", e);
		}
		return null;
	}
	
	/**
	 * 人工合并主索引-查看居民详情
	 * @throws IOException 
	 */
	@RequestMapping(params = "method=indexDetail")
	public String personDetail(String retiredPk,String survivingPk,ModelMap modelMap){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retiredMap", personIdxLogService.queryIndexDetail(retiredPk));
		map.put("survivingMap", personIdxLogService.queryIndexDetail(survivingPk));
		modelMap.putAll(map);
		modelMap.put("type", "index");
		return "query/page/show";
	}
	
	/**
	 * 拆分 - 获取原主索引详情
	 * @param splitIndex 被拆分的主索引主键
	 * @return
	 */
	@RequestMapping(params = "method=indexSplitDetail")
	public String findBeSpileIndex(String splitPk,ModelMap modelMap){
		Map<String, Object> map = new HashMap<String, Object>();
		PersonIndex personIndex = new PersonIndex();
		List<Map<String, Object>> indexs = new ArrayList<Map<String, Object>>();
		//原主索引
		indexs.add(personIdxLogService.queryIndexDetail(splitPk));
		//原主索引曾合并过主索引集合
		List<Map<String, Object>> mergeIndexs = personIdxLogService.queryIndexDetails(splitPk);
		if(mergeIndexs.size()>0){
			for (int i = 0; i < mergeIndexs.size(); i++) {
				indexs.add(mergeIndexs.get(i));
			}
		}
		personIndex.setMPI_PK(splitPk);
		map.put("splitIndex", indexs);
		map.put("MPI_PK", splitPk);
		modelMap.putAll(map);
		return "query/page/split_show";
	}
	
	
	/**
	 * 绑定主索引combobox -lose
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=indexSplitCombox")
	public String findBeMergeIndexCombox(String splitPk,HttpServletResponse response){
		List<PersonIndex> personIndexs = personIndexService.findPersonIndexBysplitIndex(splitPk);
		String mpi_pk = "";
		for (int i = 0; i < personIndexs.size(); i++) {
			mpi_pk = "'"+personIndexs.get(i).getMPI_PK()+"'"+",";
		}
		mpi_pk = mpi_pk.substring(0, mpi_pk.length()-1);
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT V.ORGCODE, V.ORGNAME													");
		sb.append("  FROM ((SELECT '0000' AS ORGCODE, '-- 请选择 --' AS ORGNAME FROM DUAL) 		");
		sb.append("         UNION																");
		sb.append("       (SELECT T.MPI_PK AS ORGCODE, T.NAME_CN AS ORGNAME				");
		sb.append("             FROM mpi_person_index T											");
			sb.append("           WHERE T.MPI_PK in (" + mpi_pk + ") ");
		sb.append(" )) V");
		RowMapper<Map<String , String>> mp = new RowMapper<Map<String , String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("ORGCODE",rs.getString("ORGCODE"));
				m.put("ORGNAME",rs.getString("ORGNAME"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, String>> list  = jdbcTemplate.query(sb.toString(),mp);
		 JSONArray json = JSONArray.fromObject(list);     
		return json.toString();
	}
	
	/**
	 * 拆分主索引信息
	 * 1.通过原主索引主键获取mpi_person_idx_log对象
	 * 2.根据mpi_person_idx_log删除原主索引现关联关系
	 * 3.根据mpi_person_idx_log重新关联到删除主索引上
	 * 4.重新合并原主索引
	 * 5.删除mpi_person_idx_log对应的关联记录
	 * 6.恢复拆分主索引为有效
	 * @param splitPK 拆分主索引
	 * @param formerPK 原主索引
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=execSlipt")
	public String execSlipt(String splitPK,String formerPK){
		try {
			personIndexService.splitIndex(splitPK, formerPK);
		}
		catch (ValidationException e) {// 验证异常
			logger.error("合并用户时发生错误", e);
		}
		catch (Exception e) {
			logger.error("系统错误,无法完成合并主索引操作", e);
		}
		return null;
	}
}
