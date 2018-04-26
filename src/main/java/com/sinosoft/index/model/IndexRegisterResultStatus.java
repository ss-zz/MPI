package com.sinosoft.index.model;

/**
 * 主索引注册结果状态
 * 
 * @author sinosoft
 *
 */
public enum IndexRegisterResultStatus {

	SUCCESS("100", "注册成功"), //
	FAIL_CHECK("901", "校验失败"), //
	FAIL_BIZ("902", "处理异常"), //
	FAIL_OTHER("999", "注册失败")//
	;

	private String code;
	private String desc;

	private IndexRegisterResultStatus(String code, String desc) {
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
