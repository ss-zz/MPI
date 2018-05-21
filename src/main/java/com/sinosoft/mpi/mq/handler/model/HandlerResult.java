package com.sinosoft.mpi.mq.handler.model;

import java.io.Serializable;

/**
 * 人员、业务数据注册处理结果
 */
public class HandlerResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 注册类型：1-人员、2-业务
	 */
	private String type;

	/**
	 * 原始人员id
	 */
	private String srcPersonId;

	/**
	 * 原始业务id，若注册类型为人员、则此信息为空
	 */
	private String srcBizId;

	/**
	 * 人员主索引id
	 */
	private String personIdxId;

	/**
	 * 业务新id
	 */
	private String bizIdxId;

	/**
	 * 业务次id
	 */
	private String bizTimeId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrcPersonId() {
		return srcPersonId;
	}

	public void setSrcPersonId(String srcPersonId) {
		this.srcPersonId = srcPersonId;
	}

	public String getSrcBizId() {
		return srcBizId;
	}

	public void setSrcBizId(String srcBizId) {
		this.srcBizId = srcBizId;
	}

	public String getPersonIdxId() {
		return personIdxId;
	}

	public void setPersonIdxId(String personIdxId) {
		this.personIdxId = personIdxId;
	}

	public String getBizTimeId() {
		return bizTimeId;
	}

	public void setBizTimeId(String bizTimeId) {
		this.bizTimeId = bizTimeId;
	}

	public String getBizIdxId() {
		return bizIdxId;
	}

	public void setBizIdxId(String bizIdxId) {
		this.bizIdxId = bizIdxId;
	}

}
