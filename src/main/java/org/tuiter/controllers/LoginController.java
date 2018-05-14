package org.tuiter.controllers;

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
import org.tuiter.beans.LoginBean;
import org.tuiter.models.User;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.ServerConstants;

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
	public ResponseEntity<User> login(@RequestBody LoginBean requestBody) throws ServletException {
		try {			
			User dbUser = userService.findByEmail(requestBody.getEmail());
			
			return new ResponseEntity<>(dbUser, HttpStatus.OK);
		} catch(RuntimeException e) {
			e.printStackTrace();
			throw new ServletException("Request error while trying to login... " + e.getMessage());
		}
	}
	
	@Autowired
	public void setUserService(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
	}	

}
