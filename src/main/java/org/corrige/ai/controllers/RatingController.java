package org.corrige.ai.controllers;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
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
				+ ServerConstants.RATING_REQUEST)
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@PostMapping 
	public ResponseEntity<Rating> create(@RequestBody RatingBean body) throws ReviewNotExistsException, EmptyFieldsException, UserNotExistsException {
		return ResponseEntity.ok(ratingService.create(body));
	}
	
	@GetMapping(value="/{id}") 
	public ResponseEntity<Object> get(@PathVariable String id) throws RatingNotExistsException {
		Rating rating = ratingService.findById(id);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody EditRatingBean body) throws RatingNotExistsException, EmptyFieldsException {
		Rating rating = ratingService.update(id, body);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Object> delete(@PathVariable String id) throws RatingNotExistsException {
		ratingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
