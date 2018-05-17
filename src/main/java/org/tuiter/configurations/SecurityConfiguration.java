package org.tuiter.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuiter.filters.AuthorizationFilter;
import org.tuiter.util.ServerConstants;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration {
//	private final CustomUserDetailsService userServiceDetails;
//	private final UserService userService;
//	
//	@Autowired
//	public SecurityConfiguration(CustomUserDetailsService userServiceDetails, UserServiceImpl userService) {
//		this.userServiceDetails = userServiceDetails;
//		this.userService = userService;
//	}*/
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.cors()
//		.and()
//		.csrf()
//		.disable()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.POST, "/login")
//		.permitAll()
//		.antMatchers(HttpMethod.POST, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST)
//		.permitAll()
//		.antMatchers(HttpMethod.GET, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/get/*")
//		.hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST)
//		.hasRole("USER")
//		.antMatchers(HttpMethod.PUT, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/edit")
//		.hasRole("USER")
//		.antMatchers(HttpMethod.DELETE, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/delete")
//		.hasRole("USER")
//		.antMatchers(HttpMethod.PATCH, ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/**/pass")
//		.hasRole("USER")
//		.and()
//		.addFilter(new JWTAuthenticationFilter(authenticationManager(), userService))
//		.addFilter(new JWTAuthorizationFilter(authenticationManager(), userServiceDetails));
//
//	}
	
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> buildAuthorizationFilter() {
		
		FilterRegistrationBean<AuthorizationFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new AuthorizationFilter());
		
		filterRegistration.addUrlPatterns(ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/edit", ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/getall");
		return filterRegistration;
	}
	
}
