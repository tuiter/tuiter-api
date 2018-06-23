package org.corrige.ai.models.user;

import org.corrige.ai.enums.Gender;
import org.corrige.ai.validations.annotations.ValidEmail;
import org.corrige.ai.validations.annotations.ValidGender;
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
	@Setter private Gender gender;
	@Setter private String name;
	@Setter private String password;
	@Getter @Setter private String photoUrl;
	@Setter private String username;

	@ValidEmail
	public String getEmail() {
		return email;
	}
	
	@ValidGender
	public Gender getGender() {
		return gender;
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