package org.corrige.ai.services.implementations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private EssayService essayService;
	
	@Override
	public Notification createOnReviewDone(String essayId, String senderId) throws UserNotExistsException, ReviewNotExistsException, EssayNotExistsException {
		Optional<User> sender = userService.findById(senderId);
		
		if(sender.isPresent()) {
			String timeStamp = generateTimeStamp(); 			
			Essay essay = essayService.findById(essayId);
			Optional<User> receiver = userService.findById(essay.getUserId());
			
			if(receiver.isPresent()) {				
				String description = "O usuário '" + sender.get().getUsername() + "' fez uma revisão na sua redação " + "'" + essay.getTitle() + "'.";
				
				Notification notification = new Notification(receiver.get().getId(), timeStamp, description, true);
				messagingTemplate.convertAndSend("/notification_ch/" + notification.getUserId(), notification);
				return notificationRepository.save(notification);
			}
		} else {
			throw new UserNotExistsException();
		}
		throw new UserNotExistsException();
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
	public Collection<Notification> findAllByUserId(String id) throws UserNotExistsException {
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent())
			return notificationRepository.findAllByUserId(user.get().getId());
		else 
			throw new UserNotExistsException();
	}

	@Override
	public Collection<Notification> findAll() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification findById(String id) throws NotificationNotExistsException {
		Optional<Notification> notification = notificationRepository.findById(id);
		
		if (notification.isPresent())
			return notification.get(); 
		else
			throw new NotificationNotExistsException();
	}
	
	@Override
	public Collection<Notification> deleteAllByUserId(String userId) throws UserNotExistsException {
		Optional<User> user = userService.findById(userId);
		
		if (user.isPresent())
			return notificationRepository.deleteAllByUserId(userId);
		else
			throw new UserNotExistsException();
	}

	@Override
	public Collection<Notification> setAllAsViewedByUser(String userId) throws UserNotExistsException {
		Collection<Notification> notifications = findAllByUserId(userId);
		
		for (Notification n : notifications) {
			if (n.isNew())
				n.setNew(false);
		}
		
		notificationRepository.saveAll(notifications);
		return notifications;
	}

}
