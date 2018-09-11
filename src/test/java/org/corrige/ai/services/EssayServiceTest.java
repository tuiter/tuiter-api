package org.corrige.ai.services;

import java.util.Date;
import java.util.Optional;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

import org.corrige.ai.enums.Type;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.EssayRepository;
import org.corrige.ai.repositories.TopicRepository;
import org.corrige.ai.repositories.UserRepository;
import org.corrige.ai.services.implementations.EssayServiceImpl;
import org.corrige.ai.services.implementations.TopicServiceImpl;
import org.corrige.ai.services.implementations.UserServiceImpl;
import org.corrige.ai.services.interfaces.TopicService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EssayServiceTest {
	@MockBean
	private EssayRepository essayRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TopicRepository topicRepository;

	@MockBean
	private UserServiceImpl userService;
	
	@Autowired
	private EssayServiceImpl service;

	@MockBean
	private TopicServiceImpl topicService;

	User user1 = new User("user1@gmail.com", "user1", "User1", "pass123", "photo1");
	User user2 = new User("user2@gmail.com", "user2", "User2", "pass123", "photo2");
	
	Optional<User> opt_user1 = Optional.of(user1);
	Optional<User> opt_user2 = Optional.of(user2);
	
	Topic topic1 = new Topic("Theme1", new Date("12/12/2010"), new Date("21/12/2010"));
	Topic topic2 = new Topic("Theme2", new Date("12/12/2010"), new Date("21/12/2010"));

	Essay essay1 = new Essay(null, "Title1", "Theme1", "Content1", Type.IMAGE, "1");
	Essay essay2 = new Essay(null, "Title2", "Theme2", "Content2", Type.TEXT, "1");
	
	EssayBean bean1 = new EssayBean("user1", "Title1", "Theme1",  "Content1", Type.IMAGE, "1");
	EssayBean bean2 = new EssayBean("user2", "Title2", "Theme2", "Content2", Type.TEXT, "1");
	
	@Test
	public void createEssayTest() {
		when(userService.findByUsername("user1")).thenReturn(opt_user1);
		when(userService.findByUsername("user2")).thenReturn(opt_user2);
		
		try {
			when(topicService.findById("1")).thenReturn(topic1);
			when(topicService.findById("2")).thenReturn(topic2);
		} catch (TopicNotExistsException e) {
		}
		
		when(essayRepository.save(essay1)).thenReturn(essay1);
		when(essayRepository.save(essay2)).thenReturn(essay2);
		
		try {
			Assert.assertEquals(essay1, this.service.create(bean1));
			Assert.assertEquals(essay2, this.service.create(bean2));
		} catch (UserNotExistsException | TopicNotExistsException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void updateEssayTest() {
		when(userService.findByUsername("user1")).thenReturn(opt_user1);
		when(userService.findByUsername("user2")).thenReturn(opt_user2);
		
		try {
			when(topicService.findById("1")).thenReturn(topic1);
			when(topicService.findById("2")).thenReturn(topic2);
		} catch (TopicNotExistsException e) {
		}
		
		when(essayRepository.save(essay1)).thenReturn(essay1);
		when(essayRepository.save(essay2)).thenReturn(essay2);
		
		try {
			Assert.assertEquals(essay1, this.service.create(bean1));
			Assert.assertEquals(essay2, this.service.create(bean2));
		} catch (UserNotExistsException | TopicNotExistsException e) {
			e.printStackTrace();
		}
		
	}

}
