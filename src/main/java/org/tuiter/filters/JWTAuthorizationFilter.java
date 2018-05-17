/*package org.tuiter.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.tuiter.models.User;
import org.tuiter.security.CustomUserDetailsService;
import org.tuiter.util.SecurityConstants;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final CustomUserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("passou por aqui " + header);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			throw new ServletException("Missing or incorrect authorization method.");
		}
		
		UsernamePasswordAuthenticationToken userAuthToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(userAuthToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		
		String username = Jwts.parser()
							  .setSigningKey(SecurityConstants.SECRET)
							  .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
							  .getBody()
							  .getSubject();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		return (username != null)  ? new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()) : null;
	}
}
*/