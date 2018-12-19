package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.error;

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

/*
Used to be thrown at the RESTController class when the user uses a pathVariable index not
existent at the list of entityModel available instances.  
*/
