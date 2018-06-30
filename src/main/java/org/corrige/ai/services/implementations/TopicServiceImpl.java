package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.topic.EditTopicBean;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.repositories.TopicRepository;
import org.corrige.ai.services.interfaces.TopicService;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Topic create(Topic topic) throws EmptyFieldsException{
		if(topic.getTheme().equals("")) {
			throw new EmptyFieldsException();
		}
		return topicRepository.save(topic);
	}

	@Override
	public Topic update(String id, EditTopicBean bean) throws TopicNotExistsException, EmptyFieldsException {
		Topic topic = getById(id);
		
		if(topic != null) {
			if(bean.getTheme().equals("")) {
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
	public Topic getOpenTopic() throws TopicNotFoundException {
		List<Topic> topics = topicRepository.findAll();
		Date now = new Date();
		for (Topic topic : topics) {
			if (topic.getEndDate().after(now)) {
				return topic;
			}
		}
		throw new TopicNotFoundException();
	}

	@Override
	public Topic addEssayToTopic(Essay essay, String topicId) throws TopicNotExistsException {
		Topic topic = getById(topicId);
		if (topic == null) {
			throw new TopicNotExistsException();
		}
		List<Essay> essays = topic.getEssays();
		essays.add(essay);
		topic.setEssays(essays);
		return topicRepository.save(topic);
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
