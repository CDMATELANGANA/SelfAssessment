package com.mars.cdma.gov.bean;

public class Login {

	String userId;
	String userPassword;
	
	public Login() {
	}
	
	
	public Login(String userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	

}
