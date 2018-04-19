package com.sinosoft.mpi.util;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 主键生成
 */

public class IDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static Long query4Long(JdbcTemplate jdbcTemplate, String sql) {
		Long id = jdbcTemplate.queryForObject(sql, Long.class);
		return id;
	}
}
