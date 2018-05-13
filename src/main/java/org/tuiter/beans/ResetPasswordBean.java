package org.tuiter.beans;

import org.tuiter.errors.annotations.ValidPassword;

public class ResetPasswordBean {
	private String email;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	public ResetPasswordBean() {
		
	}
	
	public ResetPasswordBean(String email, String oldPassword, String newPassword, String confirmNewPassword) {
		super();
		this.email = email;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	@ValidPassword
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
		
}
