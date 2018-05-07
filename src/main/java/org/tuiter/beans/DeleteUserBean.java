package org.tuiter.beans;

public class DeleteUserBean {
	private String password;
	private String username;

	public DeleteUserBean() {
		
	}

	public DeleteUserBean(String username, String password) {
		this.password = password;
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}