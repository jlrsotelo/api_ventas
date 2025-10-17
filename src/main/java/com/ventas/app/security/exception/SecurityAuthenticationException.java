package com.ventas.app.security.exception;

public class SecurityAuthenticationException extends Exception {

	private static final long serialVersionUID = -2792119423275018438L;

	public SecurityAuthenticationException() {
		
	}

	public SecurityAuthenticationException(String message) {
		super(message);
		
	}

	public SecurityAuthenticationException(Throwable cause) {
		super(cause);
		
	}

	public SecurityAuthenticationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public SecurityAuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
