package org.tuiter.controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.beans.AuthenticationResponse;
import org.tuiter.beans.LoginBean;
import org.tuiter.models.User;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.Model2BeanFactory;
import org.tuiter.util.ServerConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST +
				ServerConstants.AUTHENTICATION_REQUEST)
public class LoginController {
	private UserService userService;
	
	@RequestMapping(value = "/login",
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginBean requestBody) throws ServletException {
		User user = userService.findByEmail(requestBody.getEmail());
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
		
		if (!requestBody.getPassword().isEmpty()) {
			if (!user.getPassword().equals(requestBody.getPassword())) {
				throw new ServletException("Password is incorrect!");
			}
		}
		
		
		String token = "";	
//		String token = buildToken(user.getUsername());
		
		AuthenticationResponse response = new AuthenticationResponse(token, Model2BeanFactory.createUserBean(user));
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private String buildToken(String username) {
		SignatureAlgorithm sAlgorithm = SignatureAlgorithm.HS256;
		 
		
		Date expiration = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
		
		
		String token = Jwts.builder()
                		   .setSubject(username)
                		   .signWith(sAlgorithm, "auto123")
                		   .setExpiration(expiration)
                		   .compact();
		
		return token;
	}
	
	@Autowired
	public void setUserService(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
	}	

}
