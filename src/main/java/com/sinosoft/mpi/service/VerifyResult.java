package com.sinosoft.mpi.service;

/**
 * 验证结果
 */
public class VerifyResult {

	private Boolean success;
	private String msg;

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
