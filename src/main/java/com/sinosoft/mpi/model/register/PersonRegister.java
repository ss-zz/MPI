package com.sinosoft.mpi.model.register;

import com.sinosoft.mpi.model.MpiPersonInfoRegister;

/**
 * 人员注册信息
 */
public class PersonRegister extends BaseRegister{

	/**
	 * 人员信息
	 */
	private MpiPersonInfoRegister mpiPersonInfoRegister;

	public MpiPersonInfoRegister getMpiPersonInfoRegister() {
		return mpiPersonInfoRegister;
	}

	public void setMpiPersonInfoRegister(MpiPersonInfoRegister mpiPersonInfoRegister) {
		this.mpiPersonInfoRegister = mpiPersonInfoRegister;
	}

}
