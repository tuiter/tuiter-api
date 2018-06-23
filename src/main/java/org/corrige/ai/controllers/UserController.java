package org.corrige.ai.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.ApiError;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.USER_REQUEST)
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EssayService essayService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable String id) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user, HttpStatus.OK);				
		} else {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());				
		}
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<User>> getUsers() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> signup(@Valid @RequestBody SignupBean body) throws UserAlreadyExistsException {
		User user = userService.create(body);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> edit(@PathVariable String id, @Valid @RequestBody User body) throws UserNotExistsException {	
		User user = userService.update(id, body);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Object> delete(@PathVariable String id) throws UserNotExistsException {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/pass",
			method = RequestMethod.PATCH
			) 
	public ResponseEntity<Object> resetPassword(@PathVariable String id, @Valid @RequestBody ResetPasswordBean body) throws EmptyFieldsException, IncorretPasswordException, UserNotExistsException {
		userService.resetPassword(id, body);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/essays",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getEssaysByUser(@PathVariable String id) throws UserNotExistsException {
		Collection<Essay> essays = essayService.findAllByUserId(id);
		return new ResponseEntity<>(essays, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}/reviews",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getReviewsByUser(@PathVariable String id) throws UserNotExistsException {
		Collection<Review> reviews = reviewService.findAllByUserId(id);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/evaluate", method = RequestMethod.GET)
	public ResponseEntity<Object> getEssay(@PathVariable String id) throws UserNotExistsException, EssayNotExistsException {
		EssayToReviewResponse essayToReviewResponse = essayService.getEssayToReview(id);
		return new ResponseEntity<>(essayToReviewResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/notifications", method = RequestMethod.GET)
	public ResponseEntity<Object> getNotificationsByUser(@PathVariable String userId) throws UserNotExistsException {
		return new ResponseEntity<>(notificationService.findAllByUserId(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/notifications", method = RequestMethod.PATCH)
	public ResponseEntity<Object> viewAllNotifications(@PathVariable String userId) throws UserNotExistsException {
		return new ResponseEntity<>(notificationService.setAllAsViewedByUser(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/notifications/all", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAll(@PathVariable String userId) throws UserNotExistsException {
		notificationService.deleteAllByUserId(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/essaysReviews",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Object> getEssaysStatusByUser(@PathVariable String id) throws EssayNotExistsException, UserNotExistsException {
		Collection<EssaysReviews> reviews = essayService.getEssaysReviews(id);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
}

