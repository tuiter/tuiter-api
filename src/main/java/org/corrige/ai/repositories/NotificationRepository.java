package org.corrige.ai.repositories;

import org.corrige.ai.models.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public Iterable<Notification> findAllByUserId(String id);
	public Iterable<Notification> deleteAllByUserId(String id);
	
}
