package com.sinosoft.match;

public class MatchException extends RuntimeException {

	private static final long serialVersionUID = 1110708026830823453L;

	public MatchException() {
		super();
	}

	public MatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatchException(String message) {
		super(message);
	}

	public MatchException(Throwable cause) {
		super(cause);
	}

}
