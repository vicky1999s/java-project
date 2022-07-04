package com.vicky.authentication.exception;

public class EmailAlreadyExistsException extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
	
	public EmailAlreadyExistsException(String message, Throwable thorwable) {
		super(message, thorwable);
	}
}
