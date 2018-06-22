package org.corrige.ai.services.interfaces;

import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface NotificationService {
	public Notification createOnReviewDone(String essayId, String senderId) throws UserNotFoundException, ReviewNotExistsException, EssayNotExistsException;
	public Notification delete(String id) throws NotificationNotExistsException;
	public Iterable<Notification> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Notification> findAll();
	public Notification findById(String id) throws NotificationNotExistsException;
	public Iterable<Notification> setAllAsViewedByUser(String userId) throws UserNotFoundException, UserNotExistsException;
	public Iterable<Notification> deleteAllByUserId(String userId) throws UserNotFoundException;
}
