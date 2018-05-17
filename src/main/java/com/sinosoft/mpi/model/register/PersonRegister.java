package com.sinosoft.mpi.model.register;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * 人员注册
 */
public class PersonRegister {

	/**
	 * 业务系统唯一标识
	 */
	String systemKey;

	/**
	 * 人员信息
	 */
	PersonInfo personInfo;

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

}
