package com.sinosoft.mpi.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;

/**
 * 下拉框操作类
 */
@Controller
@RequestMapping("/")
public class ComboboxDataController {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("countZonesCombobox.ac")
	public void countZones(HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		sb.append("	select v.zonecode,v.zonename from (											");
		sb.append("		(select '0000' AS ZONECODE,'-- 请选择 --'AS  ZONENAME FROM V_ZONECODE)		");
		sb.append("		union																	");
		sb.append("		(SELECT T.DD_ID AS ZONECODE,T.DD_NAME AS  ZONENAME FROM V_ZONECODE T)	");
		sb.append("	)V order by v.zonecode														");

		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("ZONECODE", rs.getString("ZONECODE"));
				m.put("ZONENAME", rs.getString("ZONENAME"));
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
			e.printStackTrace();
		}
	}

	@RequestMapping("countOrgsOfZoneCommbobox.ac")
	public void countOrgsOfZone(String zonecode, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT V.ORGCODE, V.ORGNAME													");
		sb.append("  FROM ((SELECT '0000' AS ORGCODE, '-- 请选择 --' AS ORGNAME FROM DUAL) 		");
		sb.append("         UNION																");
		sb.append("       (SELECT T.ORGCODE AS ORGCODE, T.ORGNAME AS ORGNAME					");
		sb.append("             FROM V_JR_ORGANIZATION T											");
		if (zonecode != null && !zonecode.isEmpty()) {
			sb.append("           WHERE T.APANAGECODE = '" + zonecode + "' ");
		}
		sb.append(" )) V ORDER BY V.ORGCODE	");
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
		try {
			response.getWriter().print(datas.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("countYearsCombobox")
	public void countYears(HttpServletResponse response) {
		String sql = "SELECT V.DT FROM V_COUNT_YEARS V ORDER BY V.DT DESC";

		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("DT", rs.getString("DT"));
				return m;
			}
		};
		// 查询数据
		List<Map<String, String>> list = jdbcTemplate.query(sql, mp);
		JSONObject datas = new JSONObject();
		// 写入数据
		datas.put("rows", list);
		try {
			response.getWriter().print(datas.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
