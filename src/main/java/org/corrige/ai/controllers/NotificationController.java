package org.corrige.ai.controllers;

import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.util.ServerConstants;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.NOTIFICATION_REQUEST)
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@DeleteMapping(value = "/{notificationId}")
	public ResponseEntity<HttpStatus> delete(@PathVariable String notificationId) throws NotificationNotExistsException {
		notificationService.delete(notificationId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
