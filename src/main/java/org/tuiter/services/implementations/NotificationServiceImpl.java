package org.tuiter.services.implementations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.beans.NotificationBean;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.NotificationNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;
import org.tuiter.models.Notification;
import org.tuiter.models.User;
import org.tuiter.repositories.NotificationRepository;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.NotificationService;
import org.tuiter.services.interfaces.UserService;

@Service
public class NotificationServiceImpl implements NotificationService {
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
	public Notification createOnReviewDone(String essayId, NotificationBean bean) throws UserNotExistsException, UserNotFoundException, ReviewNotExistsException, EssayNotExistsException {
		User sender = userService.findById(bean.getSenderId());
		
		if(sender != null) {
			Instant instant = Instant.now();
			long timeStampMillis = instant.toEpochMilli();
			LocalDateTime timeStamp =  Instant.ofEpochMilli(timeStampMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy | HH:MM:ss");
			
			Essay essay = essayService.findById(essayId);
			User receiver = userService.findById(essay.getUserId());
			
			String description = "O usuário /'" + sender.getUsername() + "/' fez um revisão na sua redação: " + "/'" + essay.getTitle() + "/'.";
			
			Notification notification = new Notification(receiver.getId(), timeStamp.format(formatter), description, true);
			return notificationRepository.save(notification);
		} else {
			throw new UserNotExistsException();
		}
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
