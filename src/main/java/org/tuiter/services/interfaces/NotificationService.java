package org.tuiter.services.interfaces;

import org.tuiter.beans.modelbeans.NotificationBean;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.NotificationNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Notification;

public interface NotificationService {
	public Notification create(NotificationBean bean) throws UserNotFoundException, UserNotExistsException, ReviewNotExistsException;
	public Notification delete(String id) throws NotificationNotExistsException;
	public Iterable<Notification> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Notification> findAll();
	public Notification findById(String id) throws NotificationNotExistsException;
}
