package com.sinosoft.mpi.util;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * 
 * @Description: 主键生成
 * 
 * 
 *
 * 
 * @Package com.sinosoft.platform.etl.common
 * @author Qin,Shouxin
 * @version v1.0,2011-7-26
 * @see
 * @since v1.0
 * 
 */

public class IDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	// lpk 2012年11月19日 获得sequence的值
	public static Long query4Long(JdbcTemplate jdbcTemplate, String sql) {
		Long id = jdbcTemplate.queryForObject(sql, Long.class);
		return id;
	}
}
