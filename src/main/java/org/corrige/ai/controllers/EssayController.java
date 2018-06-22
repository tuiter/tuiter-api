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
import org.corrige.ai.beans.EditEssayBean;
import org.corrige.ai.beans.modelbeans.EssayBean;
import org.corrige.ai.errors.ApiError;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.EssayNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotExistsException;
import org.corrige.ai.models.Essay;
import org.corrige.ai.models.Review;
import org.corrige.ai.services.implementations.EssayServiceImpl;
import org.corrige.ai.services.implementations.ReviewServiceImpl;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.ESSAY_REQUEST)
public class EssayController {
	private EssayService essayService;
	private ReviewService reviewService;
	
	@Autowired
	public void setEssayService(EssayServiceImpl essayService) {
		this.essayService = essayService;
	}
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> create(@RequestBody EssayBean body) {
		try {
			Essay essay = essayService.create(body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (UserNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Object> get(@PathVariable String id) {
		try {
			Essay essay = essayService.findById(id);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EditEssayBean body) {
		try {
			Essay essay = essayService.update(id, body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		} catch (EmptyFieldsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Fields cannot be empty.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<Essay>> getAll() {
		Iterable<Essay> essay = essayService.findAll();
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Object> delete(@PathVariable String id) {
		try {
			essayService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
	
	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET) 
	public ResponseEntity<Object> getReviewsByEssayId(@PathVariable String id) {
		try {
			Iterable<Review> reviews = reviewService.findAllByEssayId(id);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
			return new ResponseEntity<>(apiError, apiError.getCode());
		}
	}
}
