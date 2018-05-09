package com.sinosoft.index.model;

/**
 * 主索引注册参数
 * 
 * @author sinosoft
 *
 */
public class IndexRegister {

	/**
	 * 业务唯一标识
	 */
	private String bizKey;

	/**
	 * 业务id
	 */
	private String bizId;

	/**
	 * 人员id
	 */
	private String personId;

	/**
	 * 数据类型-1、新增；2-编辑；3-删除。默认：1
	 */
	private String type;

	/**
	 * 数据-人员
	 */
	private String personData;

	/**
	 * 数据-业务
	 */
	private String bizData;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}

	public String getPersonData() {
		return personData;
	}

	public void setPersonData(String personData) {
		this.personData = personData;
	}

}
