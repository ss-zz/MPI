package com.sinosoft.mpi.dics;

/**
 * 人员类型
 */
public enum PersonType {

	PERSONAL("0", "个人"),
	PATIENT("1", "患者"),
	OTHER("9", "其它");

	private String code;
	private String desc;

	private PersonType(String code, String desc) {
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
