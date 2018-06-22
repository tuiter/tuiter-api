package org.corrige.ai.models.user;

import org.corrige.ai.enums.Gender;
import org.corrige.ai.validations.annotations.ValidEmail;
import org.corrige.ai.validations.annotations.ValidGender;
import org.corrige.ai.validations.annotations.ValidName;
import org.corrige.ai.validations.annotations.ValidPassword;
import org.corrige.ai.validations.annotations.ValidUsername;

public class SignupBean {
	private String email;
	private Gender gender;
	private String name;
	private String password;
	private String photoUrl;
	private String username;

	public SignupBean() {}
	
	public SignupBean(String email, Gender gender, String name, String password, String photoUrl, String username) {
		this.email = email;
		this.gender = gender;
		this.name = name;
		this.password = password;
		this.photoUrl = photoUrl;
		this.username = username;
	}

	@ValidEmail
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ValidGender
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@ValidName
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ValidPassword
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
	
	@ValidUsername
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}