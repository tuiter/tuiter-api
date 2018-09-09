package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.models.topic.TopicBean;
import org.corrige.ai.repositories.TopicRepository;
import org.corrige.ai.services.interfaces.TopicService;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.InvalidDataException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Topic create(TopicBean topic) throws EmptyFieldsException, InvalidDataException {
		Topic newTopic = new Topic(topic.getTheme(), topic.getBeginDate(), topic.getEndDate());		
		if(topic.getTheme().equals("") || topic.getBeginDate() == null || topic.getEndDate() == null) {
			throw new EmptyFieldsException();
		}
		
		if (topic.getBeginDate().after(topic.getEndDate())) {
			throw new InvalidDataException("Begin date must be before end date!");
		}
		return topicRepository.save(newTopic);
	}

	@Override
	public Topic update(String id, TopicBean bean) throws TopicNotExistsException, EmptyFieldsException {
		Topic topic = getById(id);
		
		if(topic != null) {
			if(bean.getTheme().equals("") || bean.getBeginDate() == null || bean.getEndDate() == null) {
				throw new EmptyFieldsException();
			}
			topic.setBeginDate(bean.getBeginDate());
			topic.setEndDate(bean.getEndDate());
			topic.setTheme(bean.getTheme());
			return topicRepository.save(topic);
		}
		throw new TopicNotExistsException();
	}

	@Override
	public Topic delete(String id) throws TopicNotExistsException {
		Topic topic = getById(id);
		if(topic == null) {
			throw new TopicNotExistsException();
		}
		topicRepository.delete(topic);
		return topic;
	}

	@Override
	public Optional<Topic> getOpenTopic() {
		Date now = new Date();
		return topicRepository
			.findAll()
			.stream()
			.filter(topic -> topic.getEndDate().after(now))
			.findFirst();
	}
	
	public Topic getById(String id){
		Optional<Topic> topicOpt = topicRepository.findById(id);		
		if(topicOpt.isPresent()) {
			return topicOpt.get();
		} 
		return null;
	}

	@Override
	public Topic findById(String id) throws TopicNotExistsException {
		Topic topic = getById(id);
		if(topic == null) {
			throw new TopicNotExistsException();
		}
		return topic;
	}

	@Override
	public Collection<Topic> findAll() {
		return topicRepository.findAll();
	}

}
