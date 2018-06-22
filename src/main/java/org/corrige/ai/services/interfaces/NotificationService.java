package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.notification.Notification;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface NotificationService {
	Notification createOnReviewDone(String essayId, String senderId) throws UserNotFoundException, ReviewNotExistsException, EssayNotExistsException;
	Notification delete(String id) throws NotificationNotExistsException;
	Collection<Notification> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	Collection<Notification> findAll();
	Notification findById(String id) throws NotificationNotExistsException;
	Collection<Notification> setAllAsViewedByUser(String userId) throws UserNotFoundException, UserNotExistsException;
	Collection<Notification> deleteAllByUserId(String userId) throws UserNotFoundException;
}
