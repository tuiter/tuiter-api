package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.review.ReviewBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.EssayRepository;
import org.corrige.ai.repositories.ReviewRepository;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private EssayService essayService;
	@Autowired
	private UserService userService;
	@Autowired
	private EssayRepository essayRepository;

	@Override
	public Review create(ReviewBean bean) throws UserNotExistsException, EssayNotExistsException {
		Optional<User> user = userService.findById(bean.getReviewingUserId());
		Essay essay = essayService.findById(bean.getEssayId());
		
		if(user.isPresent() && essay != null) {
			Review review = new Review(essay.getId(), user.get().getId(), bean.getComments(), bean.getRatings());
			return reviewRepository.save(review);
		} else {
			throw new UserNotExistsException();
		}
	}

	public Review create(String userId, String essayId) throws UserNotExistsException, EssayNotExistsException {
		ReviewBean bean = new ReviewBean(essayId, userId, new LinkedList<String>(), new LinkedList<Double>());
		return this.create(bean);
	}

	@Override
	public Review update(String id, EditReviewBean bean) throws ReviewNotExistsException, EmptyFieldsException, EssayNotExistsException {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		if(reviewOpt.isPresent()) {
			Review review = reviewOpt.get();
			if(!bean.getComments().isEmpty()) {
				review.setComments(bean.getComments());
				review.setRatings(bean.getRatings());
				review.setStatus(ReviewStatus.CORRECTED);

				Essay essay = essayService.findById(review.getEssayId());
				essay.setStatus(ReviewStatus.CORRECTED);

				essayRepository.save(essay);
				reviewRepository.save(review);
				return review;
			} else {
				throw new EmptyFieldsException();
			}
			
		} else {
			throw new ReviewNotExistsException();
		}
	}

	@Override
	public Review delete(String id) throws ReviewNotExistsException {
		Optional<Review> review = reviewRepository.findById(id);
		if(review.isPresent()) {
			reviewRepository.delete(review.get());
			return review.get();
		} else {
			throw new ReviewNotExistsException();
		}
	}

	@Override
	public Collection<Review> findAllByUserId(String id) throws UserNotExistsException {
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent())
			return reviewRepository.findAllByUserId(id);
		else
			throw new UserNotExistsException();
	}
	
	@Override
	public Collection<Review> findAllByEssayId(String id) throws EssayNotExistsException {
		Essay essay = essayService.findById(id);
		
		if (essay != null) {
			return reviewRepository.findAllByEssayId(id);
		} else {
			throw new EssayNotExistsException();
		}
	}
	
	@Override
	public Collection<Review> findAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Review findById(String id) throws ReviewNotExistsException {
		Optional<Review> review = reviewRepository.findById(id);
		if(review.isPresent())
			return review.get();
		else 
			throw new ReviewNotExistsException();
	}
}
