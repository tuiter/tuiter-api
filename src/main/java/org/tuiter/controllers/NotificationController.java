package org.tuiter.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tuiter.beans.modelbeans.NotificationBean;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
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
	
	@MessageMapping("/send/message")
	@SendTo("/chat")
	public Notification castNotifications(NotificationBean notificationBean) {
		//try {
			Notification notification = notificationService.create(new NotificationBean(null, null, new SimpleDateFormat("HH:mm:ss").format(new Date()), message));
			return notification;
		/*} catch(ReviewNotExistsException e) {
			throw new TuiterApiException("Review not exists!");
		} catch(UserNotExistsException e) {
			throw new TuiterApiException("User not exists!");
		} catch(UserNotFoundException e) {
			throw new TuiterApiException("User not exists!");
		}*/
	}
	
	
}
