package org.tuiter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Notification;
import org.tuiter.services.implementations.NotificationServiceImpl;
import org.tuiter.services.interfaces.NotificationService;
import org.tuiter.util.ServerConstants;

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
	
	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public Iterable<Notification> getNotifications(String userId) {
		try {
			return notificationService.findAllByUserId(userId);
		} catch(UserNotFoundException e) {
			throw new TuiterApiException("User not found!");
		}  catch(UserNotExistsException e) {
			throw new TuiterApiException("User not exists!");
		}
	}
}
