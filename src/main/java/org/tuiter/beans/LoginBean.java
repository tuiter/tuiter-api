package org.tuiter.beans;

public class LoginBean {
	private String identifier;
	private String password;
	
	public LoginBean(){
		
	}
	
	public LoginBean(String identifier, String password) {
		this.password = password;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}