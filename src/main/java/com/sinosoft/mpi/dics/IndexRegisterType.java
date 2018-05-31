package com.sinosoft.mpi.dics;

/**
 * 主索引注册类型
 */
public enum IndexRegisterType {

	ADD((short)0, "新增"),
	UPDATE((short)1, "更新"),
	DEL((short)9, "删除");

	private Short code;
	private String desc;

	private IndexRegisterType(Short code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
