package com.prontoitlabs.assignment.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String userName;
	
	private String gender;
	
	private String password;
	
	private String token;
	
	public User() {}
	
	public User(String id, String userName, String gender) {
		this.id = id;
		this.userName = userName;
		this.gender = gender;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		
		String user = "{'id':" + this.id + ",'userName':" + this.userName + ",'gender':" + this.gender + "}";
		
		return user;
	}	
}
