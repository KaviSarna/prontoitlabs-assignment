package com.prontoitlabs.assignment.dto;

public class RestResponse {

	private String errorMessage;
	private boolean status;
	private Data data;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		String details = "{'errorMessage':" + errorMessage + ",'status':" + status + ",'data':" + data + "}";
		return details;
	}
	
	public class Data {
		
		private String token;
		private String user;
		
		public Data() {}
		
		public Data(String token, String user) {
			this.token = token;
			this.user = user;
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
		}
		
		public String getUser() {
			return user;
		}
		
		public void setUser(String user) {
			this.user = user;
		}
		
		@Override
		public String toString() {
			String details = "{'token':" + token + ",'user':" + user + "}";
			return details;
		}
	}
}
