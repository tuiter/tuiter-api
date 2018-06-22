package org.corrige.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.corrige.ai.beans.EditReviewBean;
import org.corrige.ai.beans.modelbeans.ReviewBean;
import org.corrige.ai.errors.ApiError;
import org.corrige.ai.errors.ErrorCode;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.EssayNotExistsException;
import org.corrige.ai.errors.exceptions.ReviewNotExistsException;
import org.corrige.ai.errors.exceptions.TuiterApiException;
import org.corrige.ai.errors.exceptions.UserNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotFoundException;
import org.corrige.ai.models.Review;
import org.corrige.ai.services.implementations.NotificationServiceImpl;
import org.corrige.ai.services.implementations.ReviewServiceImpl;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.ServerConstants;

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
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
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
