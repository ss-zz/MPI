package com.sinosoft.mpi.log.domain;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.sinosoft.mpi.log.dao.ILogDao;
import com.sinosoft.mpi.log.model.SysLog;

public class LogManagerImpl implements ILogManager, InitializingBean {

	private ILogDao logDao = null;
	private LogContainer logContainer = null;

	private LogRunable logRunable = null;
	private boolean synchronous = true;// 是否异步
	// 如果为异步，日志条数开关
	private int logSwitch = 0;
	// 日志级别，如果日志级别小于该级别，则不记录
	private int logLevel = 0;
	// 日志是否开启，默认开启
	private boolean on = true;
	// 存储日志对象队列最大限制
	private int queueMaxSize = 200;
	// 日志线程定期工作时间间隔
	private int sleepSecond = 3;
	private Thread logThread = null;
	private int checkTimeDistance = 30 * 60 * 1000;

	/**
	 * 日志级别关系映射
	 */
	private Map<String, Integer> funcLogLevel = null;

	/**
	 * 功能点对应日志级别设置
	 */
	private Map<String, String> funcTypeLevel;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (funcLogLevel == null) {// 初始化默认映射
			funcLogLevel = new HashMap<String, Integer>(5);
			funcLogLevel.put("1", 1);
			funcLogLevel.put("2", 2);
			funcLogLevel.put("3", 3);
			funcLogLevel.put("4", 4);
		}
		if (!on)
			return;
		logContainer = new LogContainer();
		logContainer.setLogMgr(this);
		logContainer.setMaxSize(queueMaxSize);
		logRunable = new LogRunable();
		logRunable.setLogMgr(this);
		logRunable.setSleepMillis(sleepSecond * 1000);
		logThread = new Thread(logRunable);
		logThread.start();
	}

	public void batchSerializeLog() {
		List<SysLog> list = logContainer.pollSysLog(logSwitch);
		if (list == null)
			return;
		logDao.addLog(list);
		checkLogThreadRunning();
	}

	public void writeLog(SysLog log) {
		if (!on) {
			return;
		}
		if (log == null) {
			return;
		}
		// 获取功能点日志级别
		String funcLevel = funcTypeLevel.get(log.getFuncName());

		// 如果功能点级别小于日志级别开关，则不记录日志。
		if (getConvertLogLevel(funcLevel) < logLevel) {
			return;
		}
		// 初始化日志对象属性
		log.setOperateTime(new Timestamp(System.currentTimeMillis()));
		// 如果异步添加容器中,同步直接保存
		if (synchronous) {
			logContainer.add(log);
		} else {
			logDao.addLog(log);
		}
	}

	private void checkLogThreadRunning() {
		if (System.currentTimeMillis() - logRunable.getLastRunTime() > checkTimeDistance) {
			// 线程异常,如果线程死掉,重启新的
			if (!logThread.isAlive()) {
				logThread = new Thread(logRunable);
				logThread.start();
			}
		}
	}

	// 转换功能点日志级别
	private int getConvertLogLevel(String funcLevel) {
		Integer level = funcLogLevel.get(funcLevel);
		return level == null ? 0 : level;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public void setLogSwitch(int logSwitch) {
		this.logSwitch = logSwitch;
	}

	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public void setFuncLogLevel(Map<String, Integer> funcLogLevel) {
		this.funcLogLevel = funcLogLevel;
	}

	public void setQueueMaxSize(int queueMaxSize) {
		this.queueMaxSize = queueMaxSize;
	}

	public void setSleepSecond(int sleepSecond) {
		this.sleepSecond = sleepSecond;
	}

	public void setFuncTypeLevel(Map<String, String> funcTypeLevel) {
		this.funcTypeLevel = funcTypeLevel;
	}
}
