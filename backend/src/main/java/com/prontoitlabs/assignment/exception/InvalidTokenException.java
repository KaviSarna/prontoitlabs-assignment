package com.prontoitlabs.assignment.exception;

public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = -6837950038954444805L;

	private String message;
	
	public InvalidTokenException() {}
	
	public InvalidTokenException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
