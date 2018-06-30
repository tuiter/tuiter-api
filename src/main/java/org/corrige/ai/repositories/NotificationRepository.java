package org.corrige.ai.repositories;

import java.util.Collection;

import org.corrige.ai.models.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
	Collection<Notification> findAllByUserId(String id);
	Collection<Notification> deleteAllByUserId(String id);
}
