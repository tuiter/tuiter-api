package org.tuiter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tuiter.models.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public Iterable<Notification> findAllByUserId(String id);
	
}
