package com.sinosoft.mpi.model.register;

import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;

/**
 * 业务注册
 */
public class BizRegister extends PersonRegister {

	/**
	 * 业务信息
	 */
	private MpiBizInfoRegister bizInfo;

	public MpiBizInfoRegister getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(MpiBizInfoRegister bizInfo) {
		this.bizInfo = bizInfo;
	}

}
