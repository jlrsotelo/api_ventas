package com.ventas.app.security.service.authentication;

public class AuthenticationServiceException extends Exception {

	private static final long serialVersionUID = 5185715158808636084L;

	public AuthenticationServiceException() {
	}

	public AuthenticationServiceException(String message) {
		super(message);
	}

	public AuthenticationServiceException(Throwable cause) {
		super(cause);
	}

	public AuthenticationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
