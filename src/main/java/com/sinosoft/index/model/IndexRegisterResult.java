package com.sinosoft.index.model;

/**
 * 主索引注册结果
 * 
 * @author sinosoft
 *
 */
public class IndexRegisterResult {

	/**
	 * 编码
	 * 
	 * @see IndexRegisterResultStatus
	 */
	private String code;

	/**
	 * 描述
	 */
	private String message;

	public IndexRegisterResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public IndexRegisterResult(IndexRegisterResultStatus status) {
		if (status != null) {
			this.code = status.getCode();
			this.message = status.getDesc();
		}
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
