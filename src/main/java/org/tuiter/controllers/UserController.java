package org.tuiter.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.tuiter.errors.exceptions.IncorretPasswordException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.models.Essay;
import org.tuiter.errors.exceptions.UserAlreadyExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.User;
import org.tuiter.services.implementations.EssayServiceImpl;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.USER_REQUEST)
public class UserController {
	private UserService userService;
	private EssayService essayService;
	
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEssayService(EssayServiceImpl essayService) {
		this.essayService = essayService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User user;
		try {
			user = userService.findById(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<User>> getUsers() {
		
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<User> signup(@Valid @RequestBody SignupBean body) {
		try {
			User user = userService.create(body);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserAlreadyExistsException exception) {
			throw new TuiterApiException("User already exists!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ALREADY_CREATED);
		} catch (IncorretPasswordException e) {
			throw new TuiterApiException("Password and confirmPassword don't match!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INCORRECT_PASSWORD);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<User> edit(@PathVariable String id, @Valid @RequestBody User body) throws UserNotFoundException{	
		User user = userService.update(id, body);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
		try {
			userService.delete(id);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/pass",
			method = RequestMethod.PATCH
			) 
	public ResponseEntity<HttpStatus> resetPassword(@PathVariable String id, @Valid @RequestBody ResetPasswordBean body) {
		try {
			userService.resetPassword(id, body);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (IncorretPasswordException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@RequestMapping(value = "/{id}/essays",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Iterable<Essay>> getEssaysByUser(@PathVariable String id) {
		try {
			return new ResponseEntity<Iterable<Essay>>(essayService.findAllByUserId(id), HttpStatus.OK);
		} catch (UserNotExistsException e) {
			throw new TuiterApiException("User not found!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);			
		}
		
	}
}

