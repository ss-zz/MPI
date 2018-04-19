package com.sinosoft.mpi.exception;

/**
 * 基本业务操作异常
 */
public class BaseBussinessException extends MpiException {

	private static final long serialVersionUID = 8388374539376472744L;

	public BaseBussinessException() {
		super();
	}

	public BaseBussinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseBussinessException(String message) {
		super(message);
	}

	public BaseBussinessException(Throwable cause) {
		super(cause);
	}

}
