/*package org.tuiter.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tuiter.beans.LoginBean;
import org.tuiter.models.User;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.SecurityConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONValue;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authManager;
	private UserService userService;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager, UserService userService) {
		this.authManager = authManager;
		this.userService = userService;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			LoginBean loginBean = new ObjectMapper().readValue(request.getInputStream(), LoginBean.class);
			
			/*User loggingUser = userService.findByEmail(loginBean.getIdentifier());
			
			if (loggingUser == null) {
				loggingUser = userService.findByUsername(loginBean.getIdentifier());
			}
			
			if (loggingUser == null) {
				response.setStatus(HttpStatus.NOT_FOUND.value());
				return null;
			}
			
			UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginBean.getIdentifier(), loginBean.getPassword());
			Authentication authentication = authManager.authenticate(userAuthToken);
			
			return authentication;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		ZonedDateTime expirationTime = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME, ChronoUnit.MILLIS);
		
		String token = Jwts.builder()
						   .setSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername())
						   .setExpiration(Date.from(expirationTime.toInstant()))
						   .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
						   .compact();
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		
		
        
        User user = userService.findByUsername(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername());
        
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            
            node.put("token", token);
            node.putPOJO("user", user);
            
            mapper.writeValue(writer, node);
        } catch (Exception e){
        	throw new RuntimeException(e);
        }
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}
}*/
