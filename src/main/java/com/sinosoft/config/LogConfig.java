package com.sinosoft.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinosoft.mpi.log.dao.JDBCLogDao;
import com.sinosoft.mpi.log.domain.LogManagerImpl;

/**
 * 日志配置
 */
@Configuration
public class LogConfig {

	@Autowired
	JDBCLogDao logDao;

	@Bean
	public LogManagerImpl logManagerImpl() {
		LogManagerImpl logManager = new LogManagerImpl();
		logManager.setLogDao(logDao);
		// 是否异步，不设置，默认为true
		logManager.setSynchronous(false);
		// 异步下，多少条开始存储
		logManager.setLogSwitch(10);
		// 日志级别设置日志级别，如果日志级别小于该级别，则不记录。默认分为4个级别。1,2,3,4
		logManager.setLogLevel(1);
		// 日志功能是否开启
		logManager.setOn(true);

		// 日志功能点对应级别,4个级别可选:1，2，3，4
		Map<String, String> funcTypeLevel = new HashMap<>();
		funcTypeLevel.put("add.person", "2");
		funcTypeLevel.put("del.person", "2");
		funcTypeLevel.put("update.person", "2");
		logManager.setFuncTypeLevel(funcTypeLevel);

		return logManager;
	}

}
