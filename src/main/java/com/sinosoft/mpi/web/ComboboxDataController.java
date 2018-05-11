package com.sinosoft.mpi.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.context.Constant;

/**
 * 下拉框操作类
 */
@Controller
@RequestMapping("/")
public class ComboboxDataController {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 区域下拉框
	 */
	@RequestMapping("countZonesCombobox.ac")
	@ResponseBody
	public Map<String, Object> countZones() {
		Map<String, Object> datas = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		sb.append("	select v.zonecode,v.zonename from (											");
		sb.append("		(select '0000' AS ZONECODE,'-- 请选择 --'AS  ZONENAME FROM V_ZONECODE)		");
		sb.append("		union																	");
		sb.append("		(SELECT T.DD_ID AS ZONECODE,T.DD_NAME AS  ZONENAME FROM V_ZONECODE T)	");
		sb.append("	)V order by v.zonecode														");
		// 写入数据
		datas.put(Constant.PAGE_ROWS, jdbcTemplate.queryForList(sb.toString()));
		return datas;
	}

	/**
	 * 机构下拉框
	 * 
	 * @param zonecode
	 */
	@RequestMapping("countOrgsOfZoneCommbobox.ac")
	@ResponseBody
	public Map<String, Object> countOrgsOfZone(String zonecode) {
		Map<String, Object> datas = new HashMap<>();
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
		// 写入数据
		datas.put(Constant.PAGE_ROWS, jdbcTemplate.queryForList(sb.toString()));
		return datas;
	}

	/**
	 * 年下拉框
	 */
	@RequestMapping("countYearsCombobox")
	@ResponseBody
	public Map<String, Object> countYears() {
		Map<String, Object> datas = new HashMap<>();
		String sb = "SELECT V.DT FROM V_COUNT_YEARS V ORDER BY V.DT DESC";
		datas.put(Constant.PAGE_ROWS, jdbcTemplate.queryForList(sb.toString()));
		return datas;
	}

}
