package com.sinosoft.mpi.log.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sinosoft.mpi.log.model.SysLog;

/**
 * 存放日志对象的容器 可以取出单个或批量取出多个
 */
public class LogContainer {
	
	private LogManagerImpl logMgr = null;
	// 容器存储日志对象最大限制
	private int maxSize = 200;
	// 安全的日志队列
	private ConcurrentLinkedQueue<SysLog> logsQueue = new ConcurrentLinkedQueue<SysLog>();

	// 添加日志
	public void add(SysLog log) {
		if (logsQueue.size() >= maxSize)
			logMgr.batchSerializeLog();
		logsQueue.add(log);
	}

	public List<SysLog> pollSysLog(int amount) {
		List<SysLog> list = new ArrayList<SysLog>(amount);
		for (int i = 0; i < amount; i++) {
			SysLog log = logsQueue.poll();
			if (log == null)
				break;
			list.add(log);
		}
		return list.size() > 0 ? list : null;
	}

	public void setLogMgr(LogManagerImpl logMgr) {
		this.logMgr = logMgr;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

}
