package com.sinosoft.index.model;

/**
 * 主索引处理册结果
 * 
 * @author sinosoft
 *
 */
public class IndexHandlerResult {

	/**
	 * 业务唯一标识
	 */
	private String bizKey;

	/**
	 * 原始业务id
	 */
	private String bizId;

	/**
	 * 原始人员id
	 */
	private String personId;

	/**
	 * 主索引id，若处理失败则此id为空
	 */
	private String indexId;

	/**
	 * 数据类型
	 */
	private String type;

	/**
	 * 编码
	 * 
	 * @see IndexHandlerResultStatus
	 */
	private String code;

	/**
	 * 描述
	 */
	private String message;

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

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

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
