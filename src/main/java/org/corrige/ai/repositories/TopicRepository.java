package org.corrige.ai.repositories;

import org.corrige.ai.models.topic.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String>{
}
