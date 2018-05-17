package org.tuiter.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;
import org.tuiter.util.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class AuthorizationFilter extends GenericFilterBean {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_TYPE = "Bearer ";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("hello");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String header = httpRequest.getHeader(AUTHORIZATION_HEADER);
		
		if (header == null || !header.startsWith(AUTHORIZATION_TYPE)) {
			throw new ServletException("Missing or incorrect authorization method.");
		}
		
		String token = header.substring(AUTHORIZATION_TYPE.length());
		
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET)
						 .parseClaimsJws(token).getBody();
		} catch(SignatureException e) {
			throw new ServletException("Invalid token.");
		}
		
		chain.doFilter(request, response);
	}
}
