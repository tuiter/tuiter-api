package org.tuiter.services.interfaces;

import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.NotificationNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Notification;

public interface NotificationService {
	public Notification createOnReviewDone(String essayId, String senderId) throws UserNotExistsException, UserNotFoundException, ReviewNotExistsException, EssayNotExistsException;
	public Notification delete(String id) throws NotificationNotExistsException;
	public Iterable<Notification> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Notification> findAll();
	public Notification findById(String id) throws NotificationNotExistsException;
	public Iterable<Notification> setAllAsViewedByUser(String userId) throws UserNotFoundException, UserNotExistsException;
	public Iterable<Notification> deleteAllByUserId(String userId) throws UserNotFoundException;
}
