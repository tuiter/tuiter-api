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
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
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
public class EssayServiceTest {
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

	User user1 = new User("user1@gmail.com", "user1", "User1", "pass123", "photo1", null);
	User user2 = new User("user2@gmail.com", "user2", "User2", "pass123", "photo2", null);
	
	Optional<User> opt_user1 = Optional.of(user1);
	Optional<User> opt_user2 = Optional.of(user2);
	
	Topic topic1 = new Topic("Theme1", new Date("12/12/2010"), new Date("21/12/2010"));
	Topic topic2 = new Topic("Theme2", new Date("12/12/2010"), new Date("21/12/2010"));

	Essay essay1 = new Essay(null, "Title1", "Theme1", "Content1", Type.IMAGE, "1", null);
	Essay essay2 = new Essay(null, "Title2", "Theme2", "Content2", Type.TEXT, "1", null);
	
	EssayBean bean1 = new EssayBean("user1", "Title1", "Theme1",  "Content1", Type.IMAGE, "1", null);
	EssayBean bean2 = new EssayBean("user2", "Title2", "Theme2", "Content2", Type.TEXT, "1", null);
	
	List<Essay> essays = new ArrayList<>();
	
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
			bean1.setUserUsername("None");
			this.service.create(bean1);
		} catch (UserNotExistsException e) {
			System.out.println("Testing UserNotExistsException");
		} catch(TopicNotExistsException e) {
			e.printStackTrace();
		}
		
		try {
			bean1.setTopicId("5");
			bean1.setUserUsername("user1");
			this.service.create(bean1);
		} catch (UserNotExistsException e) {
			e.printStackTrace();
		} catch(TopicNotExistsException e) {
			System.out.println("Testing TopicNotExistsException");
		}
		
	}
	
	@Test
	public void updateEssayTest() {
		essay1.setId("1");
		essay2.setId("2");
		
		when(userService.findByUsername("user1")).thenReturn(opt_user1);
		when(userService.findByUsername("user2")).thenReturn(opt_user2);
		
		try {
			when(topicService.findById("1")).thenReturn(topic1);
			when(topicService.findById("2")).thenReturn(topic2);
		} catch (TopicNotExistsException e) {
		}
				
		essay1.setContent("New Content");
		essay1.setType(Type.TEXT);
		essay1.setTheme("New Theme1");
		essay1.setTitle("New Title 1");
		
		essay2.setContent("New Content2");
		essay2.setType(Type.IMAGE);
		essay2.setTheme("New Theme2");
		essay2.setTitle("New Title 2");
		
		Optional<Essay> opt_essay1 = Optional.of(essay1);
		Optional<Essay> opt_essay2 = Optional.of(essay2);
		
		when(essayRepository.findById("1")).thenReturn(opt_essay1);		
		when(essayRepository.findById("2")).thenReturn(opt_essay2);		
		
		when(essayRepository.save(essay1)).thenReturn(essay1);
		when(essayRepository.save(essay2)).thenReturn(essay2);
		
		EditEssayBean bean1 = new EditEssayBean("New Title 1", "New Theme1", "New Content", Type.TEXT, "1");
		EditEssayBean bean2 = new EditEssayBean("New Title 2", "New Theme2", "New Content2", Type.IMAGE, "2");
		
		try {
			Assert.assertEquals(essay1, this.service.update("1", bean1));
			Assert.assertEquals(essay2, this.service.update("2", bean2));
			
			bean1.setContent("");
			this.service.update("1", bean1); 
		} catch (EssayNotExistsException e) {
			e.printStackTrace();
		
		} catch (EmptyFieldsException e) {
			System.out.println("Testing EmptyFieldsException");
			
		} catch (TopicNotExistsException e) {
			e.printStackTrace();
		}
				
		try {
			bean1.setTopicId("6");
			this.service.update("1", bean1);
		} catch (EssayNotExistsException e) {
			e.printStackTrace();
		} catch (EmptyFieldsException e) {
			e.printStackTrace();
		} catch (TopicNotExistsException e) {
			System.out.println("Testing TopicNotExistsException");
		}
		
		try {
			this.service.update("4", bean1);
		} catch (EssayNotExistsException e) {
			System.out.println("Testing EssayNotExistsException");
		} catch (EmptyFieldsException e) {
			e.printStackTrace();
		} catch (TopicNotExistsException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllByUserUsernameTest() {
		Essay essay3 = new Essay("1", "Title3", "Theme3", "Content3", Type.TEXT, "1", null);
		
		essay1.setId("1");
		essay2.setId("2");
		
		user1.setId("1");
		user2.setId("2");
		
		essay1.setUserId("1");
		essay2.setUserId("2");

		when(userService.findByUsername("user1")).thenReturn(opt_user1);
		when(userService.findByUsername("user2")).thenReturn(opt_user2);
		
		Collection<Essay> essays_list1 = new ArrayList<>();
		Collection<Essay> essays_list2 = new ArrayList<>();
		
		essays_list1.add(essay1);
		essays_list1.add(essay3);
		essays_list2.add(essay2);
		
		when(essayRepository.findAllByUserId("1")).thenReturn(essays_list1);
		when(essayRepository.findAllByUserId("2")).thenReturn(essays_list2);
		
		try {
			Assert.assertEquals(essays_list1, this.service.findAllByUserUsername("user1"));
			Assert.assertEquals(essays_list2, this.service.findAllByUserUsername("user2"));
			this.service.findAllByUserUsername("user3");
		} catch (UserNotExistsException e) {
			System.out.println("Testing UserNotExistsException");
		}
		
	}
	
	@Test
	public void findAllByUserIdTest() {
		Essay essay3 = new Essay("1", "Title3", "Theme3", "Content3", Type.TEXT, "1", null);
		
		essay1.setId("1");
		essay2.setId("2");
		
		user1.setId("1");
		user2.setId("2");
		
		essay1.setUserId("1");
		essay2.setUserId("2");

		when(userService.existsById("1")).thenReturn(true);
		when(userService.existsById("2")).thenReturn(true);

		Collection<Essay> essays_list1 = new ArrayList<>();
		Collection<Essay> essays_list2 = new ArrayList<>();
		
		essays_list1.add(essay1);
		essays_list1.add(essay3);
		essays_list2.add(essay2);
		
		when(essayRepository.findAllByUserId("1")).thenReturn(essays_list1);
		when(essayRepository.findAllByUserId("2")).thenReturn(essays_list2);
		
		try {
			Assert.assertEquals(essays_list1, this.service.findAllByUserUsername("user1"));
			Assert.assertEquals(essays_list2, this.service.findAllByUserUsername("user2"));
			this.service.findAllByUserId("user3");
		} catch (UserNotExistsException e) {
			System.out.println("Testing UserNotExistsException");
		}
		
	}
	
	@Test
	public void getEssayToReviewTest() {
		essay1.setId("1");
		essay2.setId("2");
		
		user1.setId("1");
		user2.setId("2");
		
		essay1.setUserId("1");
		essay2.setUserId("2");
		
		List<String> comments_1 = new ArrayList<>();
		comments_1.add("top");
		comments_1.add("top2");
		comments_1.add("top3");
		comments_1.add("top4");
		comments_1.add("top5");
		
		List<Double> ratings_1 = new ArrayList<>();
		ratings_1.add(new Double(5));
		ratings_1.add(new Double(6));
		ratings_1.add(new Double(7));
		ratings_1.add(new Double(8));
		ratings_1.add(new Double(9));
		
		Review review1 = new Review("1", "2", comments_1, ratings_1);
		review1.setStatus(ReviewStatus.CORRECTED);
		review1.setId("1");
		
		Essay essay3 = new Essay("2", "Title3", "Theme3", "Content3", Type.TEXT, "1", null);
		essay3.setId("3");
		essay1.setStatus(ReviewStatus.CORRECTED);
		essay3.setStatus(ReviewStatus.PENDING);
		
		essays.add(essay1);
		essays.add(essay2);
		essays.add(essay3);
		
		List<String> comments_2 = new ArrayList<>();
		comments_2.add("ok");
		comments_2.add("ok2");
		comments_2.add("ok3");
		comments_2.add("ok4");
		comments_2.add("ok5");
		
		List<Double> ratings_2 = new ArrayList<>();
		ratings_2.add(new Double(5));
		ratings_2.add(new Double(4));
		ratings_2.add(new Double(3));
		ratings_2.add(new Double(2));
		ratings_2.add(new Double(1));
		
		Review review2 = new Review("1", "3", comments_2, ratings_2);
		review2.setStatus(ReviewStatus.PENDING);
		review2.setId("2");
		
		List<Review> reviews1 = new ArrayList<>();
		reviews1.add(review1);
		reviews1.add(review2);
		
		Optional<Essay> opt_essay1 = Optional.of(essay1);
		Optional<Essay> opt_essay2 = Optional.of(essay2);
		Optional<Essay> opt_essay3 = Optional.of(essay3);
		
		try {
			when(userService.findById("1")).thenReturn(user1);
			when(userService.findById("2")).thenReturn(user2);
			when(reviewService.findAllByUserId("1")).thenReturn(reviews1);
			when(reviewService.findAllByUserId("2")).thenReturn(new ArrayList<Review>());
		} catch (UserNotExistsException e) {
			e.printStackTrace();
		}
		when(essayRepository.findAll()).thenReturn(essays);
		when(essayRepository.findById("1")).thenReturn(opt_essay1);
		when(essayRepository.findById("2")).thenReturn(opt_essay2);
		when(essayRepository.findById("3")).thenReturn(opt_essay3);

		
		try {
			when(reviewService.findAllByUserId("1")).thenReturn(reviews1);
			when(reviewService.findAllByUserId("2")).thenReturn(new ArrayList<>());
		} catch (UserNotExistsException e1) {
			e1.printStackTrace();
		}
		
		EssayToReviewResponse essaToReview1 = new EssayToReviewResponse("2", essay3);

		try {
			Assert.assertEquals(essaToReview1.getReviewId(), this.service.getEssayToReview("1").getReviewId());
			Assert.assertEquals(essaToReview1.getEssay(), this.service.getEssayToReview("1").getEssay());
			this.service.getEssayToReview("2");
		} catch (EssayNotExistsException e) {
			System.out.println("Testing EssayNotExistsException");
		} catch (UserNotExistsException e) {
			e.printStackTrace();
		} catch (TopicNotExistsException e) {
			e.printStackTrace();
		} catch (TopicNotFoundException e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void getEssaysReviewsTest() {
		essay1.setId("1");
		essay2.setId("2");
		
		user1.setId("1");
		user2.setId("2");
		
		essay1.setUserId("1");
		essay2.setUserId("2");
		
		List<String> comments_1 = new ArrayList<>();
		comments_1.add("top");
		comments_1.add("top2");
		comments_1.add("top3");
		comments_1.add("top4");
		comments_1.add("top5");
		
		List<Double> ratings_1 = new ArrayList<>();
		ratings_1.add(new Double(5));
		ratings_1.add(new Double(6));
		ratings_1.add(new Double(7));
		ratings_1.add(new Double(8));
		ratings_1.add(new Double(9));
		
		Review review1 = new Review("1", "2", comments_1, ratings_1);
		review1.setStatus(ReviewStatus.CORRECTED);
		review1.setId("1");
		
		Essay essay3 = new Essay("2", "Title3", "Theme3", "Content3", Type.TEXT, "1", null);
		essay3.setId("3");
		essay3.setUserId("2");
		essay1.setStatus(ReviewStatus.CORRECTED);
		essay3.setStatus(ReviewStatus.PENDING);
		
		essays.add(essay1);
		essays.add(essay2);
		essays.add(essay3);
		
		List<String> comments_2 = new ArrayList<>();
		comments_2.add("ok");
		comments_2.add("ok2");
		comments_2.add("ok3");
		comments_2.add("ok4");
		comments_2.add("ok5");
		
		List<Double> ratings_2 = new ArrayList<>();
		ratings_2.add(new Double(5));
		ratings_2.add(new Double(4));
		ratings_2.add(new Double(3));
		ratings_2.add(new Double(2));
		ratings_2.add(new Double(1));
		
		Review review2 = new Review("1", "3", comments_2, ratings_2);
		review2.setStatus(ReviewStatus.PENDING);
		review2.setId("2");
		
		List<Review> reviews1 = new ArrayList<>();
		reviews1.add(review1);
		reviews1.add(review2);
		
		Optional<Essay> opt_essay1 = Optional.of(essay1);
		Optional<Essay> opt_essay2 = Optional.of(essay2);
		Optional<Essay> opt_essay3 = Optional.of(essay3);
		
		List<Essay> essaysuser1 = new ArrayList<>();
		essaysuser1.add(essay1);
		
		List<Essay> essaysuser2 = new ArrayList<>();
		essaysuser2.add(essay2);
		essaysuser2.add(essay3);
		
		List<Review> essayreviews2 = new ArrayList<>();
		essayreviews2.add(review1);
		
		List<Review> essayreviews3 = new ArrayList<>();
		essayreviews3.add(review2);
		
		
		try {
			when(userService.existsById("1")).thenReturn(true);
			when(userService.existsById("2")).thenReturn(true);
			when(reviewService.findAllByUserId("1")).thenReturn(reviews1);
			when(reviewService.findAllByUserId("2")).thenReturn(new ArrayList<Review>());
			when(essayRepository.findAllByUserId("1")).thenReturn(essaysuser1);
			when(essayRepository.findAllByUserId("2")).thenReturn(essaysuser2);
		} catch (UserNotExistsException e) {
			e.printStackTrace();
		}
		when(essayRepository.findAll()).thenReturn(essays);
		when(essayRepository.findById("1")).thenReturn(opt_essay1);
		when(essayRepository.findById("2")).thenReturn(opt_essay2);
		when(essayRepository.findById("3")).thenReturn(opt_essay3);
		
		
		try {
			when(reviewService.findAllByEssayId("1")).thenReturn(Collections.emptyList());
			when(reviewService.findAllByEssayId("2")).thenReturn(essayreviews2);
			when(reviewService.findAllByEssayId("3")).thenReturn(essayreviews3);
		} catch (EssayNotExistsException e) {
			e.printStackTrace();
		}
		
		SimpleEssayBean seb1 = new SimpleEssayBean(essay2.getTitle(), essay2.getTheme());
		SimpleEssayBean seb2 = new SimpleEssayBean(essay3.getTitle(), essay3.getTheme());
		
		EssaysReviews er1 = new EssaysReviews(review1, seb1.getTitle(), seb1.getTheme());
		EssaysReviews er2 = new EssaysReviews(review2, seb2.getTitle(), seb2.getTheme());
		
		List<EssaysReviews> essaysReviews = new ArrayList<>();
		essaysReviews.add(er1);
		essaysReviews.add(er2);
		
		
		try {
			Assert.assertEquals(Collections.emptyList(), this.service.getEssaysReviews("1"));
			Assert.assertEquals(essaysReviews.get(0).getReview(), this.service.getEssaysReviews("2").get(0).getReview());
			Assert.assertEquals(essaysReviews.get(0).getEssay().getTheme(), this.service.getEssaysReviews("2").get(0).getEssay().getTheme());
			Assert.assertEquals(essaysReviews.get(0).getEssay().getTitle(), this.service.getEssaysReviews("2").get(0).getEssay().getTitle());

			Assert.assertEquals(essaysReviews.get(1).getReview(), this.service.getEssaysReviews("2").get(1).getReview());
			Assert.assertEquals(essaysReviews.get(1).getEssay().getTheme(), this.service.getEssaysReviews("2").get(1).getEssay().getTheme());
			Assert.assertEquals(essaysReviews.get(1).getEssay().getTitle(), this.service.getEssaysReviews("2").get(1).getEssay().getTitle());
		} catch (EssayNotExistsException e2) {
			e2.printStackTrace();
		} catch (UserNotExistsException e2) {
			e2.printStackTrace();
		}
				
	}
	
	


}
