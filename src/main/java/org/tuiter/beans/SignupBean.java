package org.tuiter.beans;

import org.tuiter.util.Gender;

public class SignupBean {
	private String email;
	private String name;
	private String password;
	private String photoUrl;
	private String username;
	private Gender gender;

	public SignupBean() {
		
	}

	public SignupBean(String username, String email, String name, String photo_url, String password, 
			Gender gender) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.photoUrl = photo_url;
		this.username = username;
		this.gender = gender;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photo_url) {
		this.photoUrl = photo_url;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}