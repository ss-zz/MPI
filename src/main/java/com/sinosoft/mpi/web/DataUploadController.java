package com.sinosoft.mpi.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.sshl.model.DataUpload;
import com.sinosoft.mpi.sshl.model.DataUploadBusiness;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;
import com.sinosoft.mpi.sshl.service.IDataUploadDetailService;
import com.sinosoft.mpi.sshl.service.IDataUploadService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 数据上传控制器
 */
@Controller
@RequestMapping("/")
public class DataUploadController {
	
	private Logger logger = Logger.getLogger(DataUploadController.class);
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private IDataUploadService dataUploadService;

	@Resource
	private IDataUploadDetailService dataUploadDetailService;

	private String[][] transcode = { { "BS_TYPE", "1,基本信息,2,医疗服务,3,公共卫生" } };

	@RequestMapping("dataUploadMonitor.ac")
	public void dataUploadMonitor(PageInfo page, DataUpload upload, HttpServletResponse response) throws IOException {
		List<Map<String, String>> list = null;
		try {
			list = dataUploadService.queryForPageMap(upload, page);
		} catch (Throwable e) {
			logger.error("查询匹配配置的时候出现错误", e);
		}
		JSONObject datas = new JSONObject();
		// 写入数据
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		datas.put(Constant.PAGE_ROWS, list);
		response.getWriter().print(datas.toString());
	}

	@RequestMapping("dataUploadMonitorDetail.ac")
	public void dataUploadMonitorDetail(PageInfo page, DataUploadDetail uploadDetail, HttpServletResponse response)
			throws IOException {
		List<Map<String, String>> list = null;
		try {
			list = dataUploadDetailService.queryForPageMap(uploadDetail, page);
		} catch (Throwable e) {
			logger.error("查询匹配配置的时候出现错误", e);
		}
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 写入数据
		datas.put(Constant.PAGE_ROWS, list);
		response.getWriter().print(datas.toString());
	}

	@RequestMapping("dataAmountMonitorServerAll.ac")
	public void dataUploadMonitorBusiness(DataUploadBusiness uploadBusiness, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String[] servids = uploadBusiness.getChildservids().split("\\^");
		String[] tabs = uploadBusiness.getChildTabs().split("\\^");
		String[] types = uploadBusiness.getChildTypes().split("\\^");
		for (int i = 0; i < tabs.length; i++) {
			sb.append(" (select nvl(sum(upload_count),0) total, cast('" + servids[i] + "' as varchar2("
					+ servids[i].length() * 3 + ")) servId ");
			sb.append("   from ehr_upload_data t left join ehr_tablenamecode b on t.tab_id=b.table_id  ");
			sb.append("   LEFT JOIN com_hn_organise c ON t.orgcode = c.orgcode where 1=1 ");
			if (uploadBusiness.getZonecode() != null && !uploadBusiness.getZonecode().isEmpty()
					&& !uploadBusiness.getZonecode().equals("0000")) {// 机构
				sb.append(" and c.apanagecode = '" + uploadBusiness.getZonecode() + "'");
			}
			if (uploadBusiness.getOrgcode() != null && !uploadBusiness.getOrgcode().isEmpty()
					&& !uploadBusiness.getOrgcode().equals("0000")) {// 机构
				sb.append(" and t.orgcode = '" + uploadBusiness.getOrgcode() + "'");
			}
			if (uploadBusiness.getStDate() != null && !uploadBusiness.getStDate().trim().equals("")) {// 开始时间
				sb.append(" and t.upload_date >= to_date('" + uploadBusiness.getStDate() + "','yyyy-MM-dd')");
			}
			if (uploadBusiness.getEndDate() != null && !uploadBusiness.getEndDate().trim().equals("")) {// 结束时间
				sb.append(" and t.upload_date <= to_date('" + uploadBusiness.getEndDate() + "','yyyy-MM-dd')");
			}
			if (types[i].equals("table")) {// 表
				sb.append("  and t.tab_id = " + tabs[i] + ") union");
			} else {// 业务
				sb.append("  and t.tab_id in ( " + tabs[i] + " )) union");
			}
		}
		if (sb.toString().endsWith("union")) {
			sb.delete(sb.length() - 5, sb.length());
		}
		System.out.println(sb.toString());
		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("TOTAL", rs.getString("TOTAL"));
				m.put("SERVID", rs.getString("SERVID"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, String>> list = jdbcTemplate.query(sb.toString(), mp);
		JSONObject datas = new JSONObject();
		// 写入数据
		datas.put(Constant.PAGE_ROWS, list);
		try {
			response.getWriter().print(datas.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("dataUploadMonitorWeek.ac")
	public void dataUploadWeekStatistic(String zonecode, String orgcode, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				" SELECT A.BS_TYPE BS_TYPE, to_char(A.WEEK,'YYYY-MM-DD') WEEK, NVL(B.TOTALNUM, 0) TOTALNUM			");
		sb.append("    FROM (SELECT DISTINCT (B.BS_TYPE), W.WEEK				");
		sb.append("    FROM EHR_UPLOAD_DATA T				");
		sb.append("    LEFT JOIN EHR_TABLENAMECODE B ON T.TAB_ID = B.TABLE_ID, V_WEEK W) A		");
		sb.append(" LEFT JOIN (SELECT B.BS_TYPE,			");
		sb.append("    T.UPLOAD_DATE,				");
		sb.append("    NVL(SUM(T.UPLOAD_COUNT), 0) TOTALNUM				");
		sb.append("    FROM EHR_UPLOAD_DATA T		");
		sb.append("    LEFT JOIN EHR_TABLENAMECODE B ON T.TAB_ID = B.TABLE_ID		");
		sb.append("    LEFT JOIN COM_HN_ORGANISE C ON T.ORGCODE = C.ORGCODE		");
		sb.append("  WHERE 1 = 1			");
		if (zonecode != null && !zonecode.isEmpty() && !zonecode.equals("0000")) {
			sb.append("  AND C.APANAGECODE = '" + zonecode + "' ");
		}
		if (orgcode != null && !orgcode.isEmpty() && !orgcode.equals("0000")) {
			sb.append("      AND T.ORGCODE = '" + orgcode + "' ");
		}
		sb.append("    GROUP BY B.BS_TYPE, T.UPLOAD_DATE) B ON A.BS_TYPE = B.BS_TYPE		");
		sb.append("    AND A.WEEK = B.UPLOAD_DATE		");
		sb.append("     ORDER BY A.BS_TYPE, A.WEEK		");
		System.out.println(sb.toString());
		RowMapper<Map<String, Object>> mp = new RowMapper<Map<String, Object>>() {
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("BS_TYPE", rs.getString("BS_TYPE"));
				m.put("WEEK", rs.getString("WEEK"));
				m.put("TOTALNUM", rs.getInt("TOTALNUM"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, Object>> list = jdbcTemplate.query(sb.toString(), mp);
		/*
		 * for(int i=0;i<list.size();i++){ list.get(i) }
		 */
		list = handleTransCode(list, transcode);
		JSONObject datas = new JSONObject();
		// 写入数据
		datas.put(Constant.PAGE_ROWS, list);
		try {
			response.getWriter().print(datas.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 处理需要转码，但是又不存在码表的对象。例：pdf中描述为--- 0代表是，1代表否，2代表未知。
	 * 
	 * @param item
	 *            需要处理的对象
	 * @param transcode
	 *            码值序列,格式如下: String[][] transcode = { {"field1","0,是,1,否,2,未知"},
	 *            {"field2","0,是,1,否,2,未知"} };
	 * 
	 * @return
	 */
	public Map<String, Object> handleTransCode(Map<String, Object> item, String[][] transcode) {
		Map<String, Object> ret = item;
		for (String[] onefield : transcode) {
			String fieldname = onefield[0];
			String codes = onefield[1];
			// 获取码值对
			String[] codesplit = codes.split(",");
			int len = codesplit.length;
			// 需要转换的码值
			String text = String.valueOf(ret.get(fieldname));
			if (text != null && !text.isEmpty()) {
				for (int i = 0; i < len; i += 2) {
					if (text.equals(String.valueOf(codesplit[i]))) {
						ret.put(fieldname, codesplit[i + 1]);
						break;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * 处理需要转码，但是又不存在码表的对象。例：pdf中描述为--- 0代表是，1代表否，2代表未知。
	 * 
	 * @param items
	 *            需要处理的对象
	 * @param transcode
	 *            码值序列,格式如下: String[][] transcode = { {"field1","0,是,1,否,2,未知"},
	 *            {"field2","0,是,1,否,2,未知"} };
	 * 
	 * @return
	 */
	public List<Map<String, Object>> handleTransCode(List<Map<String, Object>> items, String[][] transcode) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		Iterator<Map<String, Object>> ite = items.iterator();
		while (ite.hasNext()) {
			ret.add(handleTransCode(ite.next(), transcode));
		}
		return ret;
	}

}
