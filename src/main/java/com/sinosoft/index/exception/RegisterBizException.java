package com.sinosoft.index.exception;

/**
 * 主索引注册异常-业务异常
 * 
 * @author sinosoft
 *
 */
public class RegisterBizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterBizException() {
		super();
	}

	public RegisterBizException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RegisterBizException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterBizException(String message) {
		super(message);
	}

	public RegisterBizException(Throwable cause) {
		super(cause);
	}

}
