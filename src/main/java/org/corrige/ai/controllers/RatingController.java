package org.corrige.ai.controllers;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.services.implementations.RatingServiceImpl;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.errors.ApiError;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.RATING_REQUEST)
public class RatingController {
	private RatingService ratingService;
	
	@Autowired
	public void setRatingService(RatingServiceImpl ratingService) {
		this.ratingService = ratingService;
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> create(@RequestBody RatingBean body) {
		try {
			Rating rating = ratingService.create(body);
			return new ResponseEntity<>(rating, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (ReviewNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Review not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (EmptyFieldsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Fields vote and comment cannot be empty.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Object> get(@PathVariable String id) {
		try {
			Rating rating = ratingService.findById(id);
			return new ResponseEntity<>(rating, HttpStatus.OK);
		} catch (RatingNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Rating not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EditRatingBean body) {
		try {
			Rating rating = ratingService.update(id, body);
			return new ResponseEntity<>(rating, HttpStatus.OK);
		} catch (RatingNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Rating not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (EmptyFieldsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Field vote cannot be empty.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Object> delete(@PathVariable String id) {
		try {
			ratingService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RatingNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Rating not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
}
