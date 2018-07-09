package org.corrige.ai.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable String id) throws UserNotExistsException {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);				
	}
	
	@GetMapping
	public ResponseEntity<Collection<User>> getUsers() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<User> signup(@Valid @RequestBody SignupBean body) throws UserAlreadyExistsException {
		return new ResponseEntity<>(userService.create(body), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> edit(@PathVariable String id, @Valid @RequestBody User body) throws UserNotExistsException {	
		return new ResponseEntity<>(userService.update(id, body), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<HttpStatus> delete(@PathVariable String id) throws UserNotExistsException {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping(value = "/{id}/pass") 
	public ResponseEntity<HttpStatus> resetPassword(@PathVariable String id, @Valid @RequestBody ResetPasswordBean body) throws EmptyFieldsException, IncorretPasswordException, UserNotExistsException {
		userService.resetPassword(id, body);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/essays") 
	public ResponseEntity<Collection<Essay>> getEssaysByUser(@PathVariable String id) throws UserNotExistsException {
		return new ResponseEntity<>(essayService.findAllByUserId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/reviews") 
	public ResponseEntity<Collection<Review>> getReviewsByUser(@PathVariable String id) throws UserNotExistsException {
		return new ResponseEntity<>(reviewService.findAllByUserId(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/evaluate")
	public ResponseEntity<EssayToReviewResponse> getEssay(@PathVariable String id) throws UserNotExistsException, EssayNotExistsException, TopicNotExistsException, TopicNotFoundException {
		return new ResponseEntity<>(essayService.getEssayToReview(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}/notifications")
	public ResponseEntity<Collection<Notification>> getNotificationsByUser(@PathVariable String userId) throws UserNotExistsException {
		return new ResponseEntity<>(notificationService.findAllByUserId(userId), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/{userId}/notifications")
	public ResponseEntity<Collection<Notification>> viewAllNotifications(@PathVariable String userId) throws UserNotExistsException {
		return new ResponseEntity<>(notificationService.setAllAsViewedByUser(userId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{userId}/notifications/all")
	public ResponseEntity<HttpStatus> deleteAll(@PathVariable String userId) throws UserNotExistsException {
		notificationService.deleteAllByUserId(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/essaysReviews") 
	public ResponseEntity<Collection<EssaysReviews>> getEssaysStatusByUser(@PathVariable String id) throws EssayNotExistsException, UserNotExistsException {
		return new ResponseEntity<>(essayService.getEssaysReviews(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/ratings",
			method = RequestMethod.GET
			) 
	public ResponseEntity<Iterable<Rating>> getRatingsByUser(@PathVariable String id) throws ReviewNotExistsException, UserNotExistsException {
		return ResponseEntity.ok(ratingService.findAllUsersRatings(id));
	}
}

