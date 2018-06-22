package org.corrige.ai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.corrige.ai.models.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public Iterable<Notification> findAllByUserId(String id);
	public Iterable<Notification> deleteAllByUserId(String id);
	
}
