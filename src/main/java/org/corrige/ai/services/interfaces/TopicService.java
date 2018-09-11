package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.models.topic.TopicBean;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.InvalidDataException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;

public interface TopicService {
	Topic create(TopicBean topic) throws EmptyFieldsException, InvalidDataException;
	Topic update(String id, TopicBean topic) throws TopicNotExistsException, EmptyFieldsException;
	Topic delete(String id) throws TopicNotExistsException;
	Optional<Topic> getOpenTopic();
	Topic findById(String id) throws TopicNotExistsException;
	Collection<Topic> findAll();
	
}
