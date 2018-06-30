package org.corrige.ai.controllers;

import java.util.Collection;

import org.corrige.ai.models.topic.EditTopicBean;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.services.interfaces.TopicService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
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
				+ ServerConstants.TOPIC_REQUEST)
public class TopicController {
	@Autowired
	private TopicService topicService;
	
	@PostMapping
	public ResponseEntity<Topic> create(@RequestBody Topic body) throws EmptyFieldsException {
		return new ResponseEntity<>(topicService.create(body), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}") 
	public ResponseEntity<Topic> get(@PathVariable String id) throws TopicNotExistsException {
		return new ResponseEntity<>(topicService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Topic> update(@PathVariable String id, @RequestBody EditTopicBean body)
			throws EmptyFieldsException, TopicNotExistsException {
		return new ResponseEntity<>(topicService.update(id, body), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Topic>> getAll() {
		return new ResponseEntity<>(topicService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id") 
	public ResponseEntity<Object> delete(@PathVariable String id) throws TopicNotExistsException {
		topicService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
