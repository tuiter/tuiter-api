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
import org.tuiter.beans.EditEssayBean;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.models.Essay;
import org.tuiter.models.Review;
import org.tuiter.services.implementations.EssayServiceImpl;
import org.tuiter.services.implementations.ReviewServiceImpl;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.util.ServerConstants;

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
	public ResponseEntity<Essay> create(@RequestBody EssayBean body) {
		try {
			Essay essay = essayService.create(body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (UserNotExistsException e) {
			throw new TuiterApiException("User not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Essay> get(@PathVariable String id) {
		try {
			Essay essay = essayService.findById(id);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Essay> update(@PathVariable String id, @RequestBody EditEssayBean body) {
		try {
			Essay essay = essayService.update(id, body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		} catch (EmptyFieldsException e) {
			throw new TuiterApiException("There are empty fields in request body.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMPTY_FIELDS);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<Essay>> getAll() {
		Iterable<Essay> essay = essayService.findAll();
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
		try {
			essayService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET) 
	public ResponseEntity<Iterable<Review>> getReviewsByEssayId(@PathVariable String id) {
		try {
			Iterable<Review> reviews = reviewService.findAllByEssayId(id);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
}
