package org.tuiter.beans.modelbeans;

import org.tuiter.util.Gender;

public class UserBean {
	private String username;
	private String email;
	private String name;
	private String photo_url;
	private Gender gender;
	
	public UserBean() {
		
	}
	
	public UserBean(String username, String email, String name, String photo_url, Gender gender) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.photo_url = photo_url;
		this.gender = gender;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}