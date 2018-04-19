package com.sinosoft.mpi.exception;

/**
 * 数据验证异常
 */
public class ValidationException extends BaseBussinessException {

	private static final long serialVersionUID = 5121937501010124086L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}
