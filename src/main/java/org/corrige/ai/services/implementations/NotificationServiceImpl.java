package org.corrige.ai.services.implementations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.NotificationRepository;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.NotificationService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	private NotificationRepository notificationRepository;
	private UserService userService;
	private EssayService essayService;
	
	@Autowired
	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEssayService(EssayServiceImpl essayService) {
		this.essayService = essayService;
	}
	
	@Override
	public Notification createOnReviewDone(String essayId, String senderId) throws UserNotFoundException, ReviewNotExistsException, EssayNotExistsException {
		User sender = userService.findById(senderId);
		
		if(sender != null) {
			String timeStamp = generateTimeStamp(); 			
			Essay essay = essayService.findById(essayId);
			User receiver = userService.findById(essay.getUserId());
			String description = "O usuário '" + sender.getUsername() + "' fez uma revisão na sua redação " + "'" + essay.getTitle() + "'.";
			
			Notification notification = new Notification(receiver.getId(), timeStamp, description, true);
			messagingTemplate.convertAndSend("/notification_ch/" + notification.getUserId(), notification);
			return notificationRepository.save(notification);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	private String generateTimeStamp() {
		Instant instant = Instant.now();
		long timeStampMillis = instant.toEpochMilli();
		LocalDateTime timeStamp =  Instant.ofEpochMilli(timeStampMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy - HH:MM:ss");
		return timeStamp.format(formatter);
	}

	@Override
	public Notification delete(String id) throws NotificationNotExistsException {
		Optional<Notification> notification = notificationRepository.findById(id);
		if(notification.isPresent()) {
			notificationRepository.delete(notification.get());
			return notification.get();
		} else {
			throw new NotificationNotExistsException();
		}
	}

	@Override
	public Iterable<Notification> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException {
		User user = userService.findById(id);
		
		if (user != null) {
			return notificationRepository.findAllByUserId(user.getId());
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Iterable<Notification> findAll() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification findById(String id) throws NotificationNotExistsException {
		Optional<Notification> notification = notificationRepository.findById(id);
		
		if (notification.isPresent()) {
			return notification.get(); 
		} else {
			throw new NotificationNotExistsException();
		}
	}
	
	@Override
	public Iterable<Notification> deleteAllByUserId(String userId) throws UserNotFoundException {
		Optional<User> user = Optional.of(userService.findById(userId));
		
		if (user.isPresent()) {
			return notificationRepository.deleteAllByUserId(userId);
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public Iterable<Notification> setAllAsViewedByUser(String userId) throws UserNotFoundException, UserNotExistsException {
		Iterable<Notification> notifications = findAllByUserId(userId);
		
		for (Notification n : notifications) {
			if (n.isNew()) {
				n.setNew(false);
			}
		}
		
		notificationRepository.saveAll(notifications);
		return notifications;
	}

}
