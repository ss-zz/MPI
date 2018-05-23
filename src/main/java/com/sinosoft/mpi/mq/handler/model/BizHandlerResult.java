package com.sinosoft.mpi.mq.handler.model;

/**
 * 业务数据处理完成后返回结果
 */
public class BizHandlerResult {

	/**
	 * 原始业务id
	 */
	private String srcBizId;

	/**
	 * 新生成业务id
	 */
	private String newBizId;

	/**
	 * 次id
	 */
	private String timeId;

	public String getSrcBizId() {
		return srcBizId;
	}

	public void setSrcBizId(String srcBizId) {
		this.srcBizId = srcBizId;
	}

	public String getNewBizId() {
		return newBizId;
	}

	public void setNewBizId(String newBizId) {
		this.newBizId = newBizId;
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

}
