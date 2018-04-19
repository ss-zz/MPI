package com.sinosoft.mpi.exception;

public class MpiException extends RuntimeException {

	private static final long serialVersionUID = 3207008857284751565L;

	public MpiException() {
		super();

	}

	public MpiException(String message, Throwable cause) {
		super(message, cause);

	}

	public MpiException(String message) {
		super(message);

	}

	public MpiException(Throwable cause) {
		super(cause);

	}

}
