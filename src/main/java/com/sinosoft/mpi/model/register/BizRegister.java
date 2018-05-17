package com.sinosoft.mpi.model.register;

import com.sinosoft.mpi.model.biz.MpiBizInfo;

/**
 * 业务注册
 */
public class BizRegister extends PersonRegister {

	/**
	 * 业务信息
	 */
	private MpiBizInfo bizInfo;

	public MpiBizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(MpiBizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

}
