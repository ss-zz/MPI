package com.sinosoft.mpi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;

/**
 * 主索引操作
 */
@Controller
@RequestMapping("/index/index.ac")
public class IndexOperate {

	@Resource
	private PersonIndexService personIndexService;
	@Resource
	private PersonIdxLogService personIdxLogService;
	@Resource
	private PersonIndexDao personIndexDao;

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 人工合并主主索引
	 */
	@RequestMapping(params = "method=merge")
	@ResponseBody
	public Map<String, Object> mergeIndex(String retiredPk, String survivingPk) {
		personIndexService.mergeIndex(retiredPk, survivingPk);
		return new HashMap<>();
	}

	/**
	 * 人工合并主索引-查看居民详情
	 */
	@RequestMapping(params = "method=indexDetail")
	public String personDetail(String retiredPk, String survivingPk, ModelMap modelMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retiredMap", personIdxLogService.queryIndexDetail(retiredPk));
		map.put("survivingMap", personIdxLogService.queryIndexDetail(survivingPk));
		modelMap.putAll(map);
		modelMap.put("type", "index");
		return "query/page/show";
	}

	/**
	 * 拆分 - 获取原主索引详情
	 * 
	 * @param splitIndex
	 *            被拆分的主索引主键
	 * @return
	 */
	@RequestMapping(params = "method=indexSplitDetail")
	public String findBeSpileIndex(String splitPk, ModelMap modelMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		PersonIndex personIndex = new PersonIndex();
		List<Map<String, Object>> indexs = new ArrayList<Map<String, Object>>();
		// 原主索引
		indexs.add(personIdxLogService.queryIndexDetail(splitPk));
		// 原主索引曾合并过主索引集合
		List<Map<String, Object>> mergeIndexs = personIdxLogService.queryIndexDetails(splitPk);
		if (mergeIndexs.size() > 0) {
			for (int i = 0; i < mergeIndexs.size(); i++) {
				indexs.add(mergeIndexs.get(i));
			}
		}
		personIndex.setMpiPk(splitPk);
		map.put("splitIndex", indexs);
		map.put("MPI_PK", splitPk);
		modelMap.putAll(map);
		return "query/page/split_show";
	}

	/**
	 * 绑定主索引combobox -lose
	 */
	@RequestMapping(params = "method=indexSplitCombox")
	@ResponseBody
	public List<Map<String, Object>> findBeMergeIndexCombox(String splitPk) {
		List<PersonIndex> personIndexs = personIndexService.findPersonIndexBysplitIndex(splitPk);
		String mpi_pk = "";
		for (int i = 0; i < personIndexs.size(); i++) {
			mpi_pk = "'" + personIndexs.get(i).getMpiPk() + "'" + ",";
		}
		mpi_pk = mpi_pk.substring(0, mpi_pk.length() - 1);

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT V.ORGCODE, V.ORGNAME													");
		sb.append("  FROM ((SELECT '0000' AS ORGCODE, '-- 请选择 --' AS ORGNAME FROM DUAL) 		");
		sb.append("         UNION																");
		sb.append("       (SELECT T.MPI_PK AS ORGCODE, T.NAME_CN AS ORGNAME				");
		sb.append("             FROM mpi_person_index T											");
		sb.append("           WHERE T.MPI_PK in (" + mpi_pk + ") ");
		sb.append(" )) V");
		return jdbcTemplate.queryForList(sb.toString());
	}

	/**
	 * 拆分主索引信息 1.通过原主索引主键获取mpi_person_idx_log对象 2.根据mpi_person_idx_log删除原主索引现关联关系
	 * 3.根据mpi_person_idx_log重新关联到删除主索引上 4.重新合并原主索引 5.删除mpi_person_idx_log对应的关联记录
	 * 6.恢复拆分主索引为有效
	 * 
	 * @param splitPK
	 *            拆分主索引
	 * @param formerPK
	 *            原主索引
	 * @return
	 */
	@RequestMapping(params = "method=execSlipt")
	@ResponseBody
	public Map<String, Object> execSlipt(String splitPK, String formerPK) {
		personIndexService.splitIndex(splitPK, formerPK);
		return new HashMap<>();
	}

}
