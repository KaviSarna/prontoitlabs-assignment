package com.prontoitlabs.assignment;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prontoitlabs.assignment.dto.RestResponse;
import com.prontoitlabs.assignment.dto.User;
import com.prontoitlabs.assignment.dto.UsersResponse;
import com.prontoitlabs.assignment.exception.InvalidCredentialsException;
import com.prontoitlabs.assignment.exception.InvalidTokenException;
import com.prontoitlabs.assignment.exception.InvalidUserNameException;
import com.prontoitlabs.assignment.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AssignmentApplicationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/Hello")
	public String Hello() {
		
		return "Hello";
	}

	@PostMapping("/user")
	public ResponseEntity<RestResponse> registerUser(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(user.getPassword().getBytes("UTF-8"));
		user.setPassword(new String(bytes, "UTF-8"));
		
		String token = JWTUtil.getJWTToken(user.getUserName());
		user.setToken(token);
		
		user = userService.newUser(user);
		
		RestResponse response = new RestResponse();
		response.setErrorMessage("null");
		response.setStatus(true);
		response.setData(response.new Data(user.getUserName(), user.toString()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-AUTH-TOKEN", user.getToken());
		
		headers.add( "Access-Control-Allow-Methods", "POST" ); 
		headers.add( "Access-Control-Max-Age", "1000" );
		
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<RestResponse> loginUser(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidCredentialsException, InvalidUserNameException {
		
		System.out.println("Password - " + user.getPassword());
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(user.getPassword().getBytes("UTF-8"));
		user.setPassword(new String(bytes, "UTF-8"));
		
		User userDetails = userService.findUser(user);
		
		if (userDetails == null) {
			
			throw new InvalidUserNameException();
		}
		
		if (user.getPassword().equals(userDetails.getPassword())) {
			
			RestResponse response = new RestResponse();
			response.setErrorMessage("null");
			response.setStatus(true);
			response.setData(response.new Data(user.getUserName(), userDetails.toString()));
			
			String token = JWTUtil.getJWTToken(userDetails.getUserName());
			
			userDetails.setToken(token);
			
			userService.updateUser(userDetails);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("X-AUTH-TOKEN", userDetails.getToken());
		
			headers.add( "Access-Control-Allow-Methods", "POST" ); 
			headers.add( "Access-Control-Max-Age", "1000" );
			
			return new ResponseEntity<>(response, headers, HttpStatus.OK);
			
		} else {
			
			throw new InvalidCredentialsException();
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<UsersResponse> getAllUsers(@RequestHeader("X-AUTH-TOKEN") String requestHeader, @RequestParam int page, @RequestParam int size) {
		
		int skip = page * size;
		int limit = size;
		
		List<User> users = userService.getAllUsers();
		List<User> content = users.parallelStream().skip(skip).limit(limit).collect(Collectors.toList());
		
		UsersResponse response = new UsersResponse();
		response.setErrorMessage("null");
		response.setStatus(true);
		response.setData(response.new Data(page, size, users.size()/size + 1, users.size(), content));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-AUTH-TOKEN", requestHeader);
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add( "Access-Control-Allow-Methods", "POST" ); 
		headers.add( "Access-Control-Max-Age", "1000" );
		
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
	
	@PostMapping("/user/verify-token")
	public ResponseEntity<RestResponse> verifyToken(@RequestHeader("X-AUTH-TOKEN") String requestHeader) throws InvalidTokenException {
		
		User user = userService.validateToken(requestHeader);
		
		RestResponse response = new RestResponse();
		response.setErrorMessage("null");
		response.setStatus(true);
		response.setData(response.new Data(user.getUserName(), user.toString()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-AUTH-TOKEN", requestHeader);
		headers.add("Access-Control-Allow-Origin", "*");
		
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
}
