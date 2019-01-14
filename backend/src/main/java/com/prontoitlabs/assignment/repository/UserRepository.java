package com.prontoitlabs.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prontoitlabs.assignment.dto.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByUserName(String userName);
	public User findByToken(String token);
}
