package com.sinosoft.index.model;

/**
 * 主索引处理结果状态
 * 
 * @author sinosoft
 *
 */
public enum IndexHandlerResultStatus {

	SUCCESS("100", "处理成功"), //
	FAIL_CHECK("901", "校验失败"), //
	FAIL_BIZ("902", "处理异常"), //
	FAIL_OTHER("999", "处理失败")//
	;

	private String code;
	private String desc;

	private IndexHandlerResultStatus(String code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
