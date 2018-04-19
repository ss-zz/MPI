package com.sinosoft.mpi.log.domain;

/**
 * 日志线程，监控日志容器，并写日志到数据库
 */
public class LogRunable implements Runnable {
	private LogManagerImpl logMgr = null;
	private int sleepMillis = 3 * 1000;
	private boolean running = true;
	private long lastRunTime = 0;

	public void run() {
		lastRunTime = System.currentTimeMillis();
		while (running) {
			try {
				logMgr.batchSerializeLog();
				lastRunTime = System.currentTimeMillis();
				Thread.sleep(sleepMillis);
			} catch (Exception e) {
				System.err.println("syslog thread occur exception reason=" + e.toString());
			}
		}
		System.out.println("syslog thread stop ....");
	}

	public void stop() {
		running = false;
	}

	public void setLogMgr(LogManagerImpl logMgr) {
		this.logMgr = logMgr;
	}

	public void setSleepMillis(int sleepMillis) {
		this.sleepMillis = sleepMillis;
	}

	public long getLastRunTime() {
		return lastRunTime;
	}

}
