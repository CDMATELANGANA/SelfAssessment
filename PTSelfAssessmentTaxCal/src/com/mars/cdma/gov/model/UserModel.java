package com.mars.cdma.gov.model;

import java.util.HashSet;
import java.util.Set;

public class UserModel {

	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRoleModel> userRole = new HashSet<UserRoleModel>(0);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleModel> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRoleModel> userRole) {
		this.userRole = userRole;
	}

	

}
