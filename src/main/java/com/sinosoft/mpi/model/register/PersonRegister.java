package com.sinosoft.mpi.model.register;

import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.biz.BizInfo;

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

	/**
	 * 业务信息
	 */
	private BizInfo bizInfo;

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

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

}
