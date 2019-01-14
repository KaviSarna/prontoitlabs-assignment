package com.prontoitlabs.assignment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prontoitlabs.assignment.JWTUtil;
import com.prontoitlabs.assignment.dto.User;
import com.prontoitlabs.assignment.exception.InvalidTokenException;
import com.prontoitlabs.assignment.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User newUser(User user) {
		
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	public User findUser(User user) {
		
		User userDetails = userRepository.findByUserName(user.getUserName());
		
		return userDetails;
	}
	
	public User updateUser(User user) {
		
		user = userRepository.save(user);
		
		return user;
	}

	public List<User> getAllUsers(int skip, int limit) {
		
		List<User> users = userRepository.findAll();
		
		return users.parallelStream().skip(skip).limit(limit).collect(Collectors.toList());
	}
	
	public List<User> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	public User validateToken(String token) throws InvalidTokenException {
		
		String userName = JWTUtil.verifyJWTToken(token);
		User user = userRepository.findByUserName(userName);
		
		if (user != null) {
			return user;
		} else {
			throw new InvalidTokenException();
		}
	}
	
	
	
	
}
