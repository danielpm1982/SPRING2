package com.danielpm1982.vaccinesRecord.error;

public class RegistryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public RegistryNotFoundException() {
	}
	public RegistryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public RegistryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public RegistryNotFoundException(String message) {
		super(message);
	}
	public RegistryNotFoundException(Throwable cause) {
		super(cause);
	}
}
