package org.tuiter.security.jwt;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.tuiter.security.model.AuthenticatedUser;

public class JwtAuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorization = servletRequest.getHeader("Authorization");
        if (authorization != null) {
            AuthenticatedUser user = new AuthenticatedUser(authorization);
            SecurityContextHolder.getContext().setAuthentication(user);
        }
        chain.doFilter(request, response);

    }

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}