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
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Review;
import org.tuiter.services.implementations.ReviewServiceImpl;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.REVIEW_REQUEST)
public class ReviewController {
	private ReviewService reviewService;
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Review> create(@RequestBody ReviewBean body) {
		try {
			Review review = reviewService.create(body);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(UserNotFoundException e) {
			throw new TuiterApiException("User not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		} catch (UserNotExistsException e) {
			throw new TuiterApiException("User not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		} catch(EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Review> getById(@PathVariable String id) {
		try {
			Review review = reviewService.findById(id);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(ReviewNotExistsException e) {
			throw new TuiterApiException("Review not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Review> update(@PathVariable String id, @RequestBody EditReviewBean body) {
		try {
			Review review = reviewService.update(id, body);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(EmptyFieldsException e) {
			throw new TuiterApiException("Invalid body", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMPTY_FIELDS);
		} catch (ReviewNotExistsException e) {
			throw new TuiterApiException("Review not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		} catch(EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
}
