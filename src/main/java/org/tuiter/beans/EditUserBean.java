package org.tuiter.beans;

import org.tuiter.util.Gender;

public class EditUserBean {
	private String username;
	private Gender gender;
	private String name;
	private String photoUrl;
	private String oldPassword;
	private String newPassword;
	
	public EditUserBean() {
		
	}

	public EditUserBean(String username, String name, String photo_url, String oldPassword, String newPassword) {
		super();
		this.username = username;
		this.name = name;
		this.photoUrl = photo_url;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public EditUserBean(String username, String name, String oldPassword, String newPassword) {
		this(username, name, "", oldPassword, newPassword);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
