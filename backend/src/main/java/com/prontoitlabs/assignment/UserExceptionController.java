package com.prontoitlabs.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.MongoWriteException;
import com.prontoitlabs.assignment.dto.RestResponse;
import com.prontoitlabs.assignment.exception.InvalidCredentialsException;
import com.prontoitlabs.assignment.exception.InvalidTokenException;
import com.prontoitlabs.assignment.exception.InvalidUserNameException;

import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = MongoWriteException.class)
	public ResponseEntity<RestResponse> exception(MongoWriteException mwe) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage("Username Already Exists");
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = SignatureException.class)
	public ResponseEntity<RestResponse> exception(SignatureException mwe) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage(mwe.getMessage());
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	public ResponseEntity<RestResponse> exception(MissingRequestHeaderException mwe) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage(mwe.getMessage());
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = InvalidTokenException.class)
	public ResponseEntity<RestResponse> exception(InvalidTokenException mwe) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage(mwe.getMessage());
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<RestResponse> exception(InvalidCredentialsException ice) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage("Invalid Password");
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = InvalidUserNameException.class)
	public ResponseEntity<RestResponse> exception(InvalidUserNameException ice) {
		
		RestResponse response = new RestResponse();
		response.setErrorMessage("Invalid Username");
		response.setStatus(false);
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	
}
