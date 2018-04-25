package com.sinosoft.index.exception;

/**
 * 主索引注册异常-校验异常
 * 
 * @author sinosoft
 *
 */
public class RegisterCheckException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterCheckException() {
		super();
	}

	public RegisterCheckException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RegisterCheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterCheckException(String message) {
		super(message);
	}

	public RegisterCheckException(Throwable cause) {
		super(cause);
	}

}
