package com.sinosoft.block;

import com.sinosoft.mpi.exception.MpiException;

public class BlockException extends MpiException {

	private static final long serialVersionUID = 6458194809439914074L;

	public BlockException() {

	}

	public BlockException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockException(String message) {
		super(message);
	}

	public BlockException(Throwable cause) {
		super(cause);
	}

}
