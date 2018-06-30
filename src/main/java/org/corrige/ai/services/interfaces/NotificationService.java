package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface NotificationService {
	Notification createOnReviewDone(String essayId, String senderId) throws ReviewNotExistsException, EssayNotExistsException, UserNotExistsException;
	Notification delete(String id) throws NotificationNotExistsException;
	Collection<Notification> findAllByUserId(String id) throws UserNotExistsException;
	Collection<Notification> findAll();
	Notification findById(String id) throws NotificationNotExistsException;
	Collection<Notification> setAllAsViewedByUser(String userId) throws UserNotExistsException;
	Collection<Notification> deleteAllByUserId(String userId) throws UserNotExistsException;
}
