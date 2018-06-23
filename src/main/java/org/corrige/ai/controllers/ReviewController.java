package org.corrige.ai.controllers;

import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.REVIEW_REQUEST)
public class ReviewController {
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Review> getById(@PathVariable String id) throws ReviewNotExistsException {
		Review review = reviewService.findById(id);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Review> update(@PathVariable String id, @RequestBody EditReviewBean body) throws ReviewNotExistsException, EmptyFieldsException, EssayNotExistsException, UserNotExistsException {
		Review review = reviewService.update(id, body);
		notificationService.createOnReviewDone(review.getEssayId(), review.getUserId());
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
}
