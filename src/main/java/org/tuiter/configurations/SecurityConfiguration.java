package org.tuiter.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuiter.filters.AuthorizationFilter;
import org.tuiter.util.ServerConstants;

@Configuration
public class SecurityConfiguration {
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> buildAuthorizationFilter() {
		
		FilterRegistrationBean<AuthorizationFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new AuthorizationFilter());
		
		filterRegistration.addUrlPatterns(ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/edit",
				ServerConstants.SERVER_REQUEST + ServerConstants.USER_REQUEST + "/getall");
		filterRegistration.addUrlPatterns(ServerConstants.SERVER_REQUEST + ServerConstants.ESSAY_REQUEST + "/edit");
		return filterRegistration;
	}
	
}
