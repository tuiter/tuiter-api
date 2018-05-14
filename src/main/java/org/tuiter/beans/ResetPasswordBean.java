package org.tuiter.beans;

import org.tuiter.errors.annotations.ValidPassword;

public class ResetPasswordBean {
	private String oldPassword;
	private String newPassword;

	public ResetPasswordBean() {
		
	}
	
	public ResetPasswordBean(String oldPassword, String newPassword) {
		this.newPassword = newPassword;
	}
	
	@ValidPassword
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
		
}
