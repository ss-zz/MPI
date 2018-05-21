package com.sinosoft.mpi.model.register;

public class BaseRegister {

	/**
	 * 数据注册类型:0-新增，1-更新，2-拆分
	 */
	private Short type;

	/**
	 * 业务系统唯一标识
	 */
	private String systemKey;

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}
