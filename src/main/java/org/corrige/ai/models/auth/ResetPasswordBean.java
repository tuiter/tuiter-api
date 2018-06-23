package org.corrige.ai.models.auth;

import org.corrige.ai.validations.annotations.ValidPassword;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ResetPasswordBean {
	@Getter @Setter private String oldPassword;
	@Setter private String newPassword;

	public ResetPasswordBean(String oldPassword, String newPassword) {
		this.newPassword = newPassword;
	}
	
	@ValidPassword
	public String getNewPassword() {
		return newPassword;
	}
		
}
