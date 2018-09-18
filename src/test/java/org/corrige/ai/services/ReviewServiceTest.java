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
public class ReviewServiceTest {
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

	User user1 = new User("user1@gmail.com", "user1", "User1", "pass123", "photo1");
	User user2 = new User("user2@gmail.com", "user2", "User2", "pass123", "photo2");

	Essay essay1 = new Essay(user1.getId(), "Title1", "Theme1", "Content1", Type.IMAGE, "1");
	
	Review review1 = new Review(user1.getId(), essay1.getId(), comments, ratings);
	
	ReviewBean bean1 = new ReviewBean(essay1.getId(), user2.getId(), comments, ratings);
	
	EditReviewBean bean2 = new EditReviewBean(comments, ratings);
	
	
	@Test
	public void createReviewTest() {
		Assert.assertEquals(user1.getId(), review1.getUserId());
		Assert.assertEquals(essay1.getId(), review1.getEssayId()); 
		Assert.assertEquals(comments, review1.getComments()); 
		Assert.assertEquals(ratings, review1.getRatings());
		Assert.assertEquals("Review [id=null, essayId=null, userId=null, content=[], rating=[]]", review1.toString());

		Assert.assertEquals(user1.getId(), bean1.getReviewingUserId());
		Assert.assertEquals(essay1.getId(), bean1.getEssayId());
		Assert.assertEquals(comments, bean1.getComments());
		Assert.assertEquals(ratings, bean1.getRatings());
		
		Assert.assertEquals(comments, bean2.getComments());
		Assert.assertEquals(ratings, bean2.getRatings());
		
		Review review3 = new Review();
		Review review5 = new Review();
		
		Assert.assertTrue(review3.equals(review5));
		
		Assert.assertEquals(null, review3.getComments());
		Assert.assertEquals(null, review3.getEssayId());
		Assert.assertEquals(null, review3.getId());
		Assert.assertEquals(null, review3.getRatings());
		Assert.assertEquals(null, review3.getUserId());
		Assert.assertEquals(null, review3.getStatus());	
		
		Review review4 = new Review("3", "4");
		Review review6 = new Review("3", "4");
		
		Assert.assertTrue(review4.equals(review6));
		
		String comentarios[] = null;
		
		Assert.assertEquals("4", review4.getEssayId());
		Assert.assertEquals(null, review4.getId());
		Assert.assertEquals("3", review4.getUserId());
		Assert.assertEquals(ReviewStatus.PENDING, review4.getStatus());	
	}
	
	@Test
	public void updateReviewTest() throws EmptyFieldsException, EssayNotExistsException, ReviewNotExistsException {
		Review review2 = new Review(user1.getId(), essay1.getId(), comments, ratings);
		
		Assert.assertTrue(review1.equals(review2));
		
		user2.setId("1");
		bean1.setReviewingUserId(user1.getId());
		
		comments.add("Comment 1");
		bean1.setComments(comments);
		ratings.add(850.00);
		bean1.setRatings(ratings);
		bean1.setEssayId(essay1.getId());
		
		Assert.assertEquals(user1.getId(), bean1.getReviewingUserId());
		Assert.assertEquals(comments, bean1.getComments());
		Assert.assertEquals(ratings, bean1.getRatings());
		Assert.assertEquals(essay1.getId(), bean1.getEssayId());
		
		review2.setComments(comments);
		review2.setEssayId(essay1.getId());
		review2.setId("2");
		review2.setRatings(ratings);
		review2.setUserId(user2.getId());
		review2.setStatus(ReviewStatus.CORRECTED);
		
		Assert.assertEquals(comments, review2.getComments());
		Assert.assertEquals(essay1.getId(), review2.getEssayId());
		Assert.assertEquals("2", review2.getId());
		Assert.assertEquals(ratings, review2.getRatings());
		Assert.assertEquals("1", review2.getUserId());
		Assert.assertEquals(ReviewStatus.CORRECTED, review2.getStatus());
				
	}
	
}
