package org.tuiter.services.implementations;

import java.util.Optional;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.beans.EditReviewBean;
import org.tuiter.beans.modelbeans.ReviewBean;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;
import org.tuiter.models.Review;
import org.tuiter.models.User;
import org.tuiter.util.ReviewStatus;
import org.tuiter.repositories.ReviewRepository;
import org.tuiter.repositories.EssayRepository;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.services.interfaces.UserService;
@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewRepository reviewRepository;
	private EssayService essayService;
	private UserService userService;
	private EssayRepository essayRepository;
		
	@Autowired
	public void setReviewRepository(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Autowired
	public void setEssayRepository(EssayRepository essayRepository) {
		this.essayRepository = essayRepository;
	}
	
	@Autowired
	public void setEssayService(EssayService essayService) {
		this.essayService = essayService;
	}

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Override
	public Review create(ReviewBean bean) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException {
		User user = userService.findById(bean.getReviewingUserId());
		Essay essay = essayService.findById(bean.getEssayId());
		
		if(user != null && essay != null) {
			Review review = new Review(essay.getId(), user.getId(), bean.getComments(), bean.getRatings());
			return reviewRepository.save(review);
		} else {
			throw new UserNotExistsException();
		}
	}

	public Review create(String userId, String essayId) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException {
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
	public Iterable<Review> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException{
		User user = userService.findById(id);
		
		if (user != null) {
			return reviewRepository.findAllByUserId(id);
		} else {
			throw new UserNotExistsException();
		}
	}
	
	@Override
	public Iterable<Review> findAllByEssayId(String id) throws EssayNotExistsException {
		Essay essay = essayService.findById(id);
		
		if (essay != null) {
			return reviewRepository.findAllByEssayId(id);
		} else {
			throw new EssayNotExistsException();
		}
	}
	
	@Override
	public Iterable<Review> findAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Review findById(String id) throws ReviewNotExistsException {
		Optional<Review> review = reviewRepository.findById(id);
		if(review.isPresent()) {
			return review.get();
		} else {
			throw new ReviewNotExistsException();
		}
	}
}
