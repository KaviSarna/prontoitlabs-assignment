package com.prontoitlabs.assignment.exception;

public class InvalidUserNameException extends Exception {

	private static final long serialVersionUID = -2204383705405087233L;
	
	private String message;
	
	public InvalidUserNameException() {}
	
	public InvalidUserNameException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
