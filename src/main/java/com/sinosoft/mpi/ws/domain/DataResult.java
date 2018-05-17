package com.sinosoft.mpi.ws.domain;

import java.io.Serializable;

/**
 * 数据操作结果返回
 */
public class DataResult<T> implements Serializable {

	private static final long serialVersionUID = -8466488809484500314L;
	private boolean success = true;
	private String msg;
	private T data;

	public DataResult() {
		super();
	}

	public DataResult(String msg) {
		super();
		this.msg = msg;
	}

	public DataResult(boolean success) {
		super();
		this.success = success;
	}

	public DataResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public DataResult(boolean success, String msg, Object arg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
