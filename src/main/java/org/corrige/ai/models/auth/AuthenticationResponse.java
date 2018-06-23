package org.corrige.ai.models.auth;

import org.corrige.ai.models.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthenticationResponse {
	private String token;
	private User user;
}
