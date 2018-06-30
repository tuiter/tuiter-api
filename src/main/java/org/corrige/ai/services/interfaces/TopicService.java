package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.topic.EditTopicBean;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;

public interface TopicService {
	Topic create(Topic topic) throws EmptyFieldsException;
	Topic update(String id, EditTopicBean topic) throws TopicNotExistsException, EmptyFieldsException;
	Topic delete(String id) throws TopicNotExistsException;
	Topic getOpenTopic() throws TopicNotFoundException;
	Topic addEssayToTopic(Essay essay, String topicId) throws TopicNotExistsException;
	Topic findById(String id) throws TopicNotExistsException;
	Collection<Topic> findAll();
	
}
