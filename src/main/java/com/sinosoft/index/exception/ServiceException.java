package com.sinosoft.index.exception;

/**
 * 业务异常
 * 
 * @author sinosoft
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8600104461243640056L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
