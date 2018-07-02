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
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping
	public ResponseEntity<Essay> create(@RequestBody EssayBean body) throws UserNotExistsException, TopicNotExistsException {
		return new ResponseEntity<>(essayService.create(body), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}") 
	public ResponseEntity<Essay> get(@PathVariable String id) throws EssayNotExistsException {
		return new ResponseEntity<>(essayService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Essay> update(@PathVariable String id, @RequestBody EditEssayBean body)
			throws EssayNotExistsException, EmptyFieldsException, TopicNotExistsException {
		return new ResponseEntity<>(essayService.update(id, body), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Essay>> getAll() {
		return new ResponseEntity<>(essayService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id") 
	public ResponseEntity<Object> delete(@PathVariable String id) throws EssayNotExistsException {
		essayService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/reviews") 
	public ResponseEntity<Collection<Review>> getEssayReviews(@PathVariable String id) throws EssayNotExistsException {
		return new ResponseEntity<>(reviewService.findAllByEssayId(id), HttpStatus.OK);
	}
	
}
