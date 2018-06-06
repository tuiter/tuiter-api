package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.beans.modelbeans.NotificationBean;
import org.tuiter.errors.exceptions.NotificationNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Notification;
import org.tuiter.models.Review;
import org.tuiter.models.User;
import org.tuiter.repositories.NotificationRepository;
import org.tuiter.services.interfaces.NotificationService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.services.interfaces.UserService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private NotificationRepository notificationRepository;
	private UserService userService;
	private ReviewService reviewService;
	
	@Autowired
	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}
	
	@Override
	public Notification create(NotificationBean bean) throws UserNotExistsException, UserNotFoundException, ReviewNotExistsException {
		User user = userService.findById(bean.getUserId());
		Review review = reviewService.findById(bean.getReviewId());
		
		if(user != null  && review != null) {
			Notification notification = new Notification(bean.getUserId(), bean.getReviewId(), bean.getTimeStamp(), bean.getDescription());
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

}
