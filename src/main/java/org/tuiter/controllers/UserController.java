package org.tuiter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tuiter.beans.DeleteUserBean;
import org.tuiter.beans.EditUserBean;
import org.tuiter.beans.SignupBean;
import org.tuiter.models.User;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.ServerConstants;

@Controller
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
	public ResponseEntity<Integer> getUser(@PathVariable Long id) {
		
		return null;
	}
	
	@RequestMapping(value = "/signup",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE
					) 
	public ResponseEntity<HttpStatus> signup(@RequestBody SignupBean body) {
		User user = new User(body.getUsername(), body.getEmail(), body.getName(), body.getPassword());
		userService.save(user);
		
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
	
	@RequestMapping(value = "/edit",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> edit(@RequestBody EditUserBean body) {
		User newUser = new User(body.getUsername(), body.getNewPassword(), body.getName(), body.getNewPassword());
		userService.update(newUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

