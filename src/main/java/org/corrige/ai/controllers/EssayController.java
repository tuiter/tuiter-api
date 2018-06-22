package org.corrige.ai.controllers;

import java.util.Collection;

import org.corrige.ai.models.essay.EditEssayBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
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
				+ ServerConstants.ESSAY_REQUEST)
public class EssayController {
	@Autowired
	private EssayService essayService;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Object> create(@RequestBody EssayBean body) throws UserNotExistsException {
		Essay essay = essayService.create(body);
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Object> get(@PathVariable String id) throws EssayNotExistsException {
		Essay essay = essayService.findById(id);
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EditEssayBean body) throws EssayNotExistsException, EmptyFieldsException {
		Essay essay = essayService.update(id, body);
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Iterable<Essay>> getAll() {
		Iterable<Essay> essay = essayService.findAll();
		return new ResponseEntity<>(essay, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Object> delete(@PathVariable String id) throws EssayNotExistsException {
		essayService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET) 
	public ResponseEntity<Object> getReviewsByEssayId(@PathVariable String id) throws EssayNotExistsException {
		Collection<Review> reviews = reviewService.findAllByEssayId(id);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
}
