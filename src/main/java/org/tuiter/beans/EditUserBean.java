package org.tuiter.beans;

public class EditUserBean {
	private String username;
	private String name;
	private String photo_url;
	private String oldPassword;
	private String newPassword;
	
	public EditUserBean() {
		
	}

	public EditUserBean(String username, String name, String photo_url, String oldPassword, String newPassword) {
		super();
		this.username = username;
		this.name = name;
		this.photo_url = photo_url;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public EditUserBean(String username, String name, String oldPassword, String newPassword) {
		this.username = username;
		this.name = name;
		this.photo_url = "";
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
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

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
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
