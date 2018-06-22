package org.corrige.ai.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.corrige.ai.beans.LoginBean;
import org.corrige.ai.errors.exceptions.FailedAuthenticationException;
import org.corrige.ai.models.User;
import org.corrige.ai.security.model.AuthenticatedUser;
import org.corrige.ai.services.implementations.AuthenticationService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	@Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (AuthenticatedUser.class.isAssignableFrom(authentication));
    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
            User possibleProfile = authenticationService.getUserFromToken((String) authentication.getCredentials());
            
            return new AuthenticatedUser((String) authentication.getCredentials(), possibleProfile);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new FailedAuthenticationException("Authentication failed: " + e.getMessage());
        }
	}
}