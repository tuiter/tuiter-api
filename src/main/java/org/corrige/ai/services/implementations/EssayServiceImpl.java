package org.corrige.ai.services.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.enums.Role;
import org.corrige.ai.models.essay.EditEssayBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.pack.Pack;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.topic.Topic;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.EssayRepository;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.services.interfaces.TopicService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotFoundException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssayServiceImpl implements EssayService{
	@Autowired
	private EssayRepository essayRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private PacksServiceImpl packService;

	@Override
	public Essay create(EssayBean bean) throws UserNotExistsException, TopicNotExistsException{
		Optional<User> user = userService.findByUsername(bean.getUserUsername());
		if(! user.isPresent()) {
			throw new UserNotExistsException();
		} 
		if(bean.getTopicId() != null && topicService.findById(bean.getTopicId()) == null) {
			throw new TopicNotExistsException();
		} 
		Essay essay = new Essay(user.get().getId(), bean.getTitle(), 
					bean.getTheme(), bean.getContent(), bean.getType(), 
					bean.getTopicId(), bean.getPremium());
		
		if(essay.getPremium())
			this.updatePackCounter(essay, user.get().getId());
		
		return essayRepository.save(essay);
	}
	
	private void updatePackCounter(Essay essay, String userId) {
		Optional<Pack> pack = this.packService.getMostRecentPack(userId);
		if (!pack.isPresent())
			return;
		if(pack.get().getCounter().equals(1))
			this.packService.remove(pack.get().getId());
		else
			pack.get().setCounter(pack.get().getCounter() - 1);
		this.packService.update(pack.get());
	}

	@Override
	public Essay update(String id, EditEssayBean bean) throws EssayNotExistsException, EmptyFieldsException, TopicNotExistsException{
		Optional<Essay> essay_opt = essayRepository.findById(id);
		if(essay_opt.isPresent()) {
			if(bean.getTopicId() != null && topicService.findById(bean.getTopicId()) == null) {
				throw new TopicNotExistsException();
			}
			Essay essay = essay_opt.get();
			if(bean.getTitle() != null && !bean.getTitle().isEmpty() && 
			   bean.getTheme() != null && !bean.getTheme().isEmpty() && 
			   bean.getContent() != null && !bean.getContent().isEmpty() &&
			   bean.getType() != null) {
				
				essay.setTitle(bean.getTitle());
				essay.setTheme(bean.getTheme());
				essay.setContent(bean.getContent());
				essay.setType(bean.getType());
				essay.setTopicId(bean.getTopicId());
				
				return essayRepository.save(essay);
			} else {
				throw new EmptyFieldsException();
			}
			
		} else {
			throw new EssayNotExistsException();
		}
	}

	@Override
	public Essay delete(String id) throws EssayNotExistsException {
		Optional<Essay> essay = essayRepository.findById(id);
		if(essay.isPresent()) {
			essayRepository.delete(essay.get());
			return essay.get();
		} else {
			throw new EssayNotExistsException();
		}
	}

	@Override
	public Collection<Essay> findAllByUserUsername(String username) throws UserNotExistsException{
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) 
			return essayRepository.findAllByUserId(user.get().getId());
		else 
			throw new UserNotExistsException();
	}

	@Override
	public Collection<Essay> findAllByUserId(String id) throws UserNotExistsException{
		if (this.userService.existsById(id)) 
			return essayRepository.findAllByUserId(id);
		throw new UserNotExistsException();
	}
	
	@Override
	public Collection<Essay> findAll() {
		return essayRepository.findAll();
	}

	@Override
	public Optional<Essay> findByTitleAndUserId(String id, String userId) {
		return essayRepository.findByTitleAndUserId(id, userId);
	}

	@Override
	public Essay findById(String id) throws EssayNotExistsException {
		Optional<Essay> essay = essayRepository.findById(id);
		if(essay.isPresent())
			return essay.get();
		else
			throw new EssayNotExistsException();
	}

	@Override
	public EssayToReviewResponse getEssayToReview(String id) 
				throws EssayNotExistsException, UserNotExistsException, TopicNotExistsException, TopicNotFoundException {
		
		Optional<Review> previousReview = getPreviousReview(id);		
		if(previousReview.isPresent())
			return new EssayToReviewResponse(previousReview.get().getId(), 
						essayRepository.findById(previousReview.get().getEssayId()).get());
		
		User user = userService.findById(id);
		
		Collection<Essay> essays = this.getEssays(user.getRole());
		Optional<Topic> topic = null;
		
		if (user.getUsingWeekelyTopic()) {
			topic = topicService.getOpenTopic();
			essays = filterEssaysByWeeklyTopic(essays, topic);		
 		}
		
		Collection<Essay> notReviewedEssays;
		
		if(topic == null)
			topic = topicService.getOpenTopic();
		
		if(this.userService.findById(id).getUsingWeekelyTopic() && topic.isPresent())
			notReviewedEssays = filterPendingEssays(getEssaysByTopic(this.topicService.getOpenTopic().get().getId()));
		else
			notReviewedEssays = filterPendingEssays(essays);
		
		EssayToReviewResponse response = filterEssaysAndCreateReview(notReviewedEssays, id);
		
		if(response == null)
			response = filterEssaysAndCreateReview(essays, id);
		
		if(response != null)
			return response;
		
		throw new EssayNotExistsException("There are no essays available for review at this time.");
	}
	
	private Collection<Essay> getEssays(Role role) {
		if (role.equals(Role.TEACHER)) {
			return this.essayRepository.findByPremium(true).stream().sorted().collect(Collectors.toList());
		}
		return this.essayRepository.findAll().stream().sorted().collect(Collectors.toList());
	}
	
	private Optional<Review> getPreviousReview(String userId) throws UserNotExistsException {
		return reviewService.findAllByUserId(userId).stream()
			.filter(review -> review.getStatus().equals(ReviewStatus.PENDING))
			.filter(review -> essayRepository.findById(review.getEssayId()).isPresent())
			.findFirst();
	}
	
	private Collection<Essay> filterEssaysByWeeklyTopic(Collection<Essay> essays, Optional<Topic> theme) {
		if(theme.isPresent()) {
			String themeName = theme.get().getTheme();
			return essays.stream()
						.filter(essay -> essay.getTheme().equals(themeName))
						.collect(Collectors.toList());
		}
		return essays;
	}
	
	private EssayToReviewResponse filterEssaysAndCreateReview(Collection<Essay> essays, String userId) throws UserNotExistsException {
		for (Essay essay : essays) {
			if (!essay.getUserId().equals(userId) 
						&& !userAlreadyReview(reviewService.findAllByUserId(userId), essay.getId())) {
				
				Review review = reviewService.create(userId, essay.getId());
				return new EssayToReviewResponse(review.getId(), essay);
			}
		}
		return null;
	}
	
	private Boolean userAlreadyReview(Collection<Review> reviews, String essayId) {
		return reviews.stream()
				.filter(review -> review.getEssayId().equals(essayId))
				.findFirst()
				.isPresent();
	}
	
	private List<Essay> filterPendingEssays(Collection<Essay> essays) {
		return essays.stream().filter(essay -> essay.getStatus().equals(ReviewStatus.PENDING))
				.collect(Collectors.toList());
	}

	@Override
	public List<EssaysReviews> getEssaysReviews(String userId) throws EssayNotExistsException, UserNotExistsException {
		Collection<Essay> essays = findAllByUserId(userId);
		List<EssaysReviews> reviews_list = new ArrayList<>();
		for (Essay essay : essays) {
			Iterable<Review> reviews = reviewService.findAllByEssayId(essay.getId());
			for (Review review : reviews) {
				reviews_list.add(new EssaysReviews(review, essay.getTitle(), essay.getTheme()));
			}
		}
		return reviews_list;
	}

	@Override
	public Collection<Essay> getEssaysByTopic(String topicId) throws TopicNotExistsException {
		return essayRepository.findAll()
			.stream()
			.filter(essay -> essay.getTopicId() != null && essay.getTopicId().equals(topicId))
			.collect(Collectors.toList());
	}

	@Override
	public OptionalDouble getEssayRating(String essayId) {
		return this.reviewService
			.findAllByEssayId(essayId)
			.stream()
			.map(review -> review.getRatings())
			.map(ratings -> ratings.stream().mapToDouble(f -> f).sum())
			.mapToDouble(s -> s).average();
	}
}
