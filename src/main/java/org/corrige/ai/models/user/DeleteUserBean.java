package org.corrige.ai.models.user;

import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.validations.annotations.ValidPassword;
import org.corrige.ai.validations.annotations.ValidUsername;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserBean {
	private String username;
	private String password;

	@ValidPassword
	public String getPassword() {
		return password;
	}
	
	@ValidUsername
	public String getUsername() {
		return username;
	}
}