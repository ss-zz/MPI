package com.sinosoft.mpi.ws;

import java.io.Serializable;

/**   
*    
* @Description webservice 数据操作结果返回
* 
* 
*
* 
* @Package com.sinosoft.mpi.ws 
* @author Bysun
* @version v1.0,2012-3-22
* @see	
* @since	（可选）	
*   
*/ 
public class DataResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8466488809484500314L;
	private boolean success = true;
	private String msg;
		
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

	
	
}
