package com.sinosoft.mpi.model.search;

/**
 * 业务查询结果
 */
public class BizSearchResult {

	/**
	 * 原始业务id
	 */
	private String bizId;

	/**
	 * 患者id
	 */
	private String bizPatientId;

	/**
	 * 患者就诊流水号ID(标识同一次就诊)
	 */
	private String bizSerialId;

	/**
	 * 业务唯一标识
	 */
	private String bizSystemId;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizPatientId() {
		return bizPatientId;
	}

	public void setBizPatientId(String bizPatientId) {
		this.bizPatientId = bizPatientId;
	}

	public String getBizSerialId() {
		return bizSerialId;
	}

	public void setBizSerialId(String bizSerialId) {
		this.bizSerialId = bizSerialId;
	}

	public String getBizSystemId() {
		return bizSystemId;
	}

	public void setBizSystemId(String bizSystemId) {
		this.bizSystemId = bizSystemId;
	}

}
