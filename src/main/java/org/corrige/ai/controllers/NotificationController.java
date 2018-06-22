package org.corrige.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.corrige.ai.errors.exceptions.NotificationNotExistsException;
import org.corrige.ai.errors.exceptions.TuiterApiException;
import org.corrige.ai.services.implementations.NotificationServiceImpl;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.util.ServerConstants;

@Controller
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST 
				+ ServerConstants.NOTIFICATION_REQUEST)
public class NotificationController {
	private NotificationService notificationService;
	
	@Autowired
	public void setNotificationService(NotificationServiceImpl notificationService) {
		this.notificationService = notificationService;
	}
	
	@RequestMapping(value = "/{notificationId}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> delete(@PathVariable String notificationId) {
		try {
			notificationService.delete(notificationId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(NotificationNotExistsException e) {
			throw new TuiterApiException("Notification not exists!");
		}
	}
}
