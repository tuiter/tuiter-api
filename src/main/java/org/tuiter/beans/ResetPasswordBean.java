package org.tuiter.beans;

import org.tuiter.errors.annotations.ValidPassword;

public class ResetPasswordBean {
	private String newPassword;

	public ResetPasswordBean() {
		
	}
	
	public ResetPasswordBean( String newPassword) {
		this.newPassword = newPassword;
	}
	
	@ValidPassword
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
		
}
