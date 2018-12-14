package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.error;

public class MyIndexOutOfBoundsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MyIndexOutOfBoundsException() {
		super();
	}
	public MyIndexOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public MyIndexOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
	public MyIndexOutOfBoundsException(String message) {
		super(message);
	}
	public MyIndexOutOfBoundsException(Throwable cause) {
		super(cause);
	}
}
