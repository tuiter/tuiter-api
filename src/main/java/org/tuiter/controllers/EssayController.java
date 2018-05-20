package org.tuiter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tuiter.beans.DeleteEssayBean;
import org.tuiter.beans.EditEssayBean;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.models.Essay;
import org.tuiter.services.implementations.EssayServiceImpl;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.util.ServerConstants;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.ESSAY_REQUEST)
public class EssayController {
private EssayService essayService;
	
	@Autowired
	public void setEssayService(EssayServiceImpl essayService) {
		this.essayService = essayService;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<Essay> create(@RequestBody EssayBean body) {
		try {
			Essay essay = essayService.create(body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (UserNotExistsException e) {
			throw new TuiterApiException("User not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<Essay> get(@PathVariable String id) {
		try {
			Essay essay = essayService.findById(id);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/edit",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<Essay> update(@RequestBody EditEssayBean body) {
		try {
			Essay essay = essayService.update(body);
			return new ResponseEntity<>(essay, HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		} catch (EmptyFieldsException e) {
			throw new TuiterApiException("Fields can not be empty.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DEFAULT);
		}
	}
	
	@RequestMapping(value = "/delete",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> delete(@RequestBody DeleteEssayBean body) {
		try {
			essayService.delete(body.getEssayId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/delete/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
		try {
			essayService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EssayNotExistsException e) {
			throw new TuiterApiException("Essay not found.", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.NOT_FOUND);
		}
	}
}
