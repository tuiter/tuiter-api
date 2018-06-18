package org.tuiter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.beans.EditReviewBean;
import org.tuiter.beans.modelbeans.ReviewBean;
import org.tuiter.errors.ApiError;
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Review;
import org.tuiter.services.implementations.NotificationServiceImpl;
import org.tuiter.services.implementations.ReviewServiceImpl;
import org.tuiter.services.interfaces.NotificationService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.REVIEW_REQUEST)
public class ReviewController {
	private NotificationService notificationService;
	private ReviewService reviewService;	
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}
	
	@Autowired
	public void setNotificatonService(NotificationServiceImpl notificationService) {
		this.notificationService = notificationService;
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> create(@RequestBody ReviewBean body) {
		try {
			Review review = reviewService.create(body);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (UserNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch(EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Object> getById(@PathVariable String id) {
		try {
			Review review = reviewService.findById(id);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(ReviewNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Review not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EditReviewBean body) {
		try {
			Review review = reviewService.update(id, body);
			notificationService.createOnReviewDone(review.getEssayId(), review.getUserId());
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(UserNotFoundException e) {
			throw new TuiterApiException ("User not found!");
		} catch(UserNotExistsException e) {
			throw new TuiterApiException ("User not found!");
		} catch(EmptyFieldsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Fields cannot be empty.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (ReviewNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Review not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch(EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
}
