package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.error;

public class MyRegistryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MyRegistryNotFoundException() {
	}
	public MyRegistryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public MyRegistryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public MyRegistryNotFoundException(String message) {
		super(message);
	}
	public MyRegistryNotFoundException(Throwable cause) {
		super(cause);
	}
}
