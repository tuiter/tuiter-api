package org.corrige.ai.models.user;

import org.corrige.ai.validations.annotations.ValidPassword;
import org.corrige.ai.validations.annotations.ValidUsername;

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