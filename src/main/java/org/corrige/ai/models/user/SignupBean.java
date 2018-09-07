package org.corrige.ai.models.user;

import org.corrige.ai.enums.Role;
import org.corrige.ai.validations.annotations.ValidEmail;
import org.corrige.ai.validations.annotations.ValidName;
import org.corrige.ai.validations.annotations.ValidPassword;
import org.corrige.ai.validations.annotations.ValidUsername;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SignupBean {
	@Setter private String email;
	@Setter private String name;
	@Setter private String password;
	@Getter @Setter private String photoUrl;
	@Setter private String username;
	@Getter private Role role;

	@ValidEmail
	public String getEmail() {
		return email;
	}

	@ValidName
	public String getName() {
		return name;
	}

	@ValidPassword
	public String getPassword() {
		return password;
	}
	
	@ValidUsername
	public String getUsername() {
		return username;
	}
	
}