package com.bridgelabz.exception;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final int status;

	public AuthorizationException(String message, int status) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
