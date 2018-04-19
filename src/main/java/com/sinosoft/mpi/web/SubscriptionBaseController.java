package com.sinosoft.mpi.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class SubscriptionBaseController {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询二级机构和卫生局
	 */
	@RequestMapping("countOrgsAndHealDepartCommbobox.ac")
	public void countOrgsAndHealDepart(String zonecode, HttpServletResponse response) throws IOException {
		// 生成sql
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT V.ORGCODE, V.ORGNAME ");
		sb.append("  FROM ((SELECT '0000' AS ORGCODE, '-- 请选择 --' AS ORGNAME FROM DUAL) ");
		sb.append("  UNION (SELECT T.ORGCODE AS ORGCODE, T.ORGNAME AS ORGNAME			");
		sb.append("             FROM V_JR_ORGANIZATION T ");
		if (zonecode != null && !zonecode.isEmpty()) {
			sb.append("           WHERE T.APANAGECODE = '" + zonecode + "' ");
		}
		sb.append(" )) V ORDER BY V.ORGCODE	");
		// 匹配方式
		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("ORGCODE", rs.getString("ORGCODE"));
				m.put("ORGNAME", rs.getString("ORGNAME"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, String>> list = jdbcTemplate.query(sb.toString(), mp);
		JSONObject datas = new JSONObject();
		// 写入数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
	}

	/**
	 * 查询订阅发布主题列表
	 */
	@RequestMapping("countSubThemesCombobox.ac")
	public void countSubThemes(HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT T.TOPIC_TYPE_CD TOPIC_CODE, ");
		sb.append("       T.TOPIC_NAME TOPIC_NAME ");
		sb.append("FROM PS_TOPIC_TYPE T ");
		// 匹配方式
		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("TOPIC_CODE", rs.getString("TOPIC_CODE"));
				m.put("TOPIC_NAME", rs.getString("TOPIC_NAME"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, String>> list = jdbcTemplate.query(sb.toString(), mp);
		JSONObject datas = new JSONObject();
		// 写入数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
	}
}
