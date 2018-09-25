package org.corrige.ai.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.enums.Type;
import org.corrige.ai.models.essay.EditEssayBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.essay.SimpleEssayBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.review.ReviewBean;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.EssayRepository;
import org.corrige.ai.repositories.TopicRepository;
import org.corrige.ai.repositories.UserRepository;
import org.corrige.ai.services.implementations.EssayServiceImpl;
import org.corrige.ai.services.implementations.ReviewServiceImpl;
import org.corrige.ai.services.implementations.TopicServiceImpl;
import org.corrige.ai.services.implementations.UserServiceImpl;
import org.corrige.ai.util.Vote;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {
	@MockBean
	private EssayRepository essayRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TopicRepository topicRepository;

	@MockBean
	private UserServiceImpl userService;
	
	@MockBean
	private ReviewServiceImpl reviewService;
	
	@Autowired
	private EssayServiceImpl service;

	@MockBean
	private TopicServiceImpl topicService;
	
	List<String> comments = new ArrayList<>();
	List<Double> ratings = new ArrayList<>();
	
	User user1 = new User("user1@gmail.com", "user1", "User1", "pass123", "photo1", null);
	
	Essay essay1 = new Essay(user1.getId(), "Title1", "Theme1", "Content1", Type.IMAGE, "1", null);
	
	Review review1 = new Review(user1.getId(), essay1.getId(), comments, ratings);
	
	Rating rating1 = new Rating();
	Rating rating2 = new Rating(user1.getId(), review1.getEssayId(), Vote.UPVOTE, "Comment1");
	
	
	RatingBean bean1 = new RatingBean();
	RatingBean bean2 = new RatingBean("1", review1.getId(), Vote.DOWNVOTE, "Comment2");
	RatingBean bean3 = new RatingBean("1", review1.getId(), Vote.DOWNVOTE);
	
	@Test
	public void createRatingTest() {
		Assert.assertEquals(null, rating1.getUserId());
		Assert.assertEquals(null, rating1.getUserId());
		Assert.assertEquals(null, rating1.getUserId());
		Assert.assertEquals(null, rating1.getUserId());
		Assert.assertEquals(null, rating1.getUserId());		
		Assert.assertEquals("Rating [id=null, userId=null, reviewId=null, vote=null, comment=null]", rating1.toString());
		
		Assert.assertEquals(null, rating2.getUserId());
		Assert.assertEquals(null, rating2.getReviewId());
		Assert.assertEquals(Vote.UPVOTE, rating2.getVote()); 
		Assert.assertEquals("Comment1", rating2.getComment());
		Assert.assertEquals(null, rating2.getId());				
		
		Assert.assertEquals(null, bean1.getUserId());
		Assert.assertEquals(null, bean1.getReviewId());
		Assert.assertEquals(null, bean1.getVote());
		Assert.assertEquals(null, bean1.getComment());
		
		Assert.assertEquals("1", bean2.getUserId());
		Assert.assertEquals(review1.getEssayId(), bean2.getReviewId());
		Assert.assertEquals(Vote.DOWNVOTE, bean2.getVote());
		Assert.assertEquals("Comment2", bean2.getComment());
	}
	
	
	@Test
	public void updateRatingTest() {
		Review review1 = new Review(user1.getId(), essay1.getId(), comments, ratings);
		Rating rating2 = new Rating(user1.getId(), review1.getEssayId(), Vote.UPVOTE, "Comment1");
		
		rating2.setComment("Another Comment");
		rating2.setId("1");
		rating2.setReviewId(review1.getId());
		rating2.setUserId(user1.getId());
		rating2.setVote(Vote.DOWNVOTE);
		
		Assert.assertEquals("Another Comment", rating2.getComment());
		Assert.assertEquals("1", rating2.getId());
		Assert.assertEquals(review1.getId(), rating2.getReviewId());
		Assert.assertEquals("1", rating2.getId());
		Assert.assertEquals(Vote.DOWNVOTE, rating2.getVote());
		
		bean2.setComment("Another comment");
		bean2.setReviewId(review1.getId());
		bean2.setUserId(user1.getId());
		bean2.setVote(Vote.UPVOTE);
		
		Assert.assertEquals("Another comment",bean2.getComment());
		Assert.assertEquals(review1.getId(),bean2.getReviewId());
		Assert.assertEquals(user1.getId(),bean2.getUserId());
		Assert.assertEquals(Vote.UPVOTE,bean2.getVote());
		
	}
	
}
