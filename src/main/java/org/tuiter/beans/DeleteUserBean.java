package org.tuiter.beans;

import org.tuiter.errors.annotations.ValidPassword;
import org.tuiter.errors.annotations.ValidUsername;

public class DeleteUserBean {
	private String password;
	private String username;

	public DeleteUserBean() {
		
	}

	public DeleteUserBean(String username, String password) {
		this.password = password;
		this.username = username;
	}

	@ValidPassword
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ValidUsername
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}