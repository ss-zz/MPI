package com.sinosoft.mpi.model.register;

public class BaseRegister {

	/**
	 * 数据注册状态:0-新增，1-更新，9-删除
	 */
	private Short state;

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

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}
