package com.prontoitlabs.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends Exception {
	
	private static final long serialVersionUID = -1571828021617207669L;
	
	private String message;
	
	public InvalidCredentialsException() {}
	
	public InvalidCredentialsException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
