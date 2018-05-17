/*package org.tuiter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	private UserService userService;
	
	@Autowired
	private void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		org.tuiter.models.User appUser = userService.findByEmail(identifier);
		
		if (appUser == null) {
			appUser = userService.findByUsername(identifier);
		}
		
		if (appUser == null) {
			throw new UsernameNotFoundException("Username not found in the database!");
		}
	
		return User.withDefaultPasswordEncoder().username(appUser.getUsername()).password(appUser.getPassword()).roles("USER").build();
	}
}*/
