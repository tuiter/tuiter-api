package org.tuiter.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.beans.ResetPasswordBean;
import org.tuiter.beans.SignupBean;
import org.tuiter.errors.ApiError;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.IncorretPasswordException;
import org.tuiter.errors.exceptions.UserAlreadyExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;
import org.tuiter.models.Review;
import org.tuiter.models.User;
import org.tuiter.services.implementations.EssayServiceImpl;
import org.tuiter.services.implementations.ReviewServiceImpl;
import org.tuiter.services.implementations.UserServiceImpl;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.ServerConstants;
import org.tuiter.beans.EssayToReviewResponse;
import org.tuiter.beans.EssaysReviews;


@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.USER_REQUEST)
public class UserController {
	private UserService userService;
	private EssayService essayService;
	private ReviewService reviewService;
	
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEssayService(EssayServiceImpl essayService) {
		this.essayService = essayService;
	}
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable String id) {
		User user;
		try {
			user = userService.findById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<User>> getUsers() {
		
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> signup(@Valid @RequestBody SignupBean body) {
		try {
			User user = userService.create(body);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserAlreadyExistsException exception) {
			ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "User already exists.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> edit(@PathVariable String id, @Valid @RequestBody User body) {	
		User user;
		try {
			user = userService.update(id, body);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Object> delete(@PathVariable String id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}/pass",
			method = RequestMethod.PATCH
			) 
	public ResponseEntity<Object> resetPassword(@PathVariable String id, @Valid @RequestBody ResetPasswordBean body) {
		try {
			userService.resetPassword(id, body);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (IncorretPasswordException e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Incorrect password.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (EmptyFieldsException e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Field newPassword field name cannot be empty.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}/essays",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getEssaysByUser(@PathVariable String id) {
		try {
			Iterable<Essay> essays = essayService.findAllByUserId(id);
			return new ResponseEntity<>(essays, HttpStatus.OK);
		} catch (UserNotExistsException e1) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());			
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
		
	}
	
	@RequestMapping(value = "/{id}/reviews",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getReviewsByUser(@PathVariable String id) {
		try {
			Iterable<Review> reviews = reviewService.findAllByUserId(id);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (UserNotExistsException e1) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());			
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
		
	}
	
	@RequestMapping(value="/{id}/evaluate", method = RequestMethod.GET)
	public ResponseEntity<Object> getEssay(@PathVariable String id) {
		try {
			EssayToReviewResponse essayToReviewResponse = essayService.getEssayToReview(id);
			return new ResponseEntity<>(essayToReviewResponse, HttpStatus.OK);
		} catch (EssayNotExistsException e) {	
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "There are no essays available.");
			return new ResponseEntity<>(apiError, apiError.getCode());	
		} catch (UserNotExistsException e1) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());			
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}/essaysReviews",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getEssaysStatusByUser(@PathVariable String id) {
		try {
			Iterable<EssaysReviews> reviews;
			reviews = essayService.getEssaysReviews(id);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (UserNotExistsException e1) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());			
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not exist.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
		
	}
}

