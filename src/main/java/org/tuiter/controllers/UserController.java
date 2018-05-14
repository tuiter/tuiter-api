package org.tuiter.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.beans.DeleteUserBean;
import org.tuiter.beans.EditUserBean;
import org.tuiter.beans.ResetPasswordBean;
import org.tuiter.beans.SignupBean;
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.models.User;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.Bean2ModelFactory;
import org.tuiter.util.ServerConstants;

@RestController
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.USER_REQUEST)
public class UserController {
	private UserService userService;
	
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/get/{id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE
					) 
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User user = userService.findById(id);
				
		if (user == null) {
			throw new TuiterApiException("User not found!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
		
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/all",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE
					) 
	public ResponseEntity<Iterable<User>> getUsers() {
		
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/signup",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE
					) 
	public ResponseEntity<HttpStatus> signup(@Valid @RequestBody SignupBean body) {
		User user = Bean2ModelFactory.createUser(body);

		if (userService.findByUsername(user.getUsername()) != null) {
			throw new TuiterApiException("Username already in the database!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ALREADY_CREATED);
		}
		
		if (userService.findByEmail(user.getEmail()) != null) {
			throw new TuiterApiException("Email already in the database!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ALREADY_CREATED);
		}
		
		userService.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> edit(@RequestBody EditUserBean body) {
		User user = userService.findByUsername(body.getUsername());
		
		if (user == null) {
			throw new TuiterApiException("User not found!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);			
		}
		
		if (!body.getName().isEmpty()) {
			user.setName(body.getName());
		}
		
		if (!body.getNewPassword().isEmpty()) {
			if (!body.getOldPassword().equals(user.getPassword())) {
				throw new TuiterApiException("Password is incorrect!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INCORRECT_PASSWORD);
			}
		}
		
		if (!body.getPhotoUrl().isEmpty()) {
			user.setPhotoUrl(body.getPhotoUrl());
		}
		
		user.setGender(body.getGender());
	
		userService.update(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> delete(@RequestBody DeleteUserBean body) {
		User user = userService.findByUsername(body.getUsername());
		userService.delete(user.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
		userService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/pass",
			method = RequestMethod.PATCH
			) 
	public ResponseEntity<HttpStatus> resetPassword(@PathVariable String id, @Valid @RequestBody ResetPasswordBean body) {
		User user = userService.findById(id);
		
		if (user == null) {
			throw new TuiterApiException("User not found!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);			
		}
		
		if (!user.getPassword().equals(body.getOldPassword()))
			throw new TuiterApiException("Password incorrect!", HttpStatus.UNAUTHORIZED, ErrorCode.INCORRECT_PASSWORD);
		
		if (body.getNewPassword().isEmpty()) {
			throw new TuiterApiException("Password is empty!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INCORRECT_PASSWORD);
		}
		
		user.setPassword(body.getNewPassword());
		
		userService.update(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

