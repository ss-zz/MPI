package com.sinosoft.mpi.model.register;

/**
 * 业务注册
 */
public class BizRegisterMq {

	/**
	 * 人员注册之后生成的新主键
	 */
	private String personFieldPk;

	/**
	 * 业务注册数据
	 */
	private BizRegister bizRegister;

	public String getPersonFieldPk() {
		return personFieldPk;
	}

	public void setPersonFieldPk(String personFieldPk) {
		this.personFieldPk = personFieldPk;
	}

	public BizRegister getBizRegister() {
		return bizRegister;
	}

	public void setBizRegister(BizRegister bizRegister) {
		this.bizRegister = bizRegister;
	}

}
