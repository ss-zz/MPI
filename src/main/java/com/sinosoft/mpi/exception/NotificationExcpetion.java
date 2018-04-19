package com.sinosoft.mpi.exception;

/**
 * 事件响应异常
 */
public class NotificationExcpetion extends MpiException {

	private static final long serialVersionUID = -1662046223903252193L;

	public NotificationExcpetion() {

	}

	public NotificationExcpetion(String message, Throwable cause) {
		super(message, cause);
	}

	public NotificationExcpetion(String message) {
		super(message);
	}

	public NotificationExcpetion(Throwable cause) {
		super(cause);
	}

}
