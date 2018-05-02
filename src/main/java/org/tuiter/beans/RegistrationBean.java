package org.tuiter.beans;

public class RegistrationBean {
	private String username;
	private String email;
	private String name;
	private String photo_url;
	private String password;

public RegistrationBean() {
	
}

public RegistrationBean(String username, String email, String name, String photo_url, String password) {
	this.username = username;
	this.email = email;
	this.name = name;
	this.photo_url = photo_url;
	this.password = password;
	
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhoto_url() {
	return photo_url;
}

public void setPhoto_url(String photo_url) {
	this.photo_url = photo_url;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}