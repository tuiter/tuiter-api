package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.beans.EditEssayBean;
import org.tuiter.beans.EditReviewBean;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.beans.modelbeans.ReviewBean;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;
import org.tuiter.models.Review;
import org.tuiter.models.User;
import org.tuiter.repositories.ReviewRepository;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.ReviewService;
import org.tuiter.services.interfaces.UserService;
@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewRepository reviewRepository;
	private EssayService essayService;
	private UserService userService;
		
	@Autowired
	public void setReviewRepository(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
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
			Review review = new Review(essay.getId(), user.getId(), bean.getTitle(), bean.getContent(), bean.getRating());
			return reviewRepository.save(review);
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Review update(String id, EditReviewBean bean) throws ReviewNotExistsException, EmptyFieldsException{
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		if(reviewOpt.isPresent()) {
			Review review = reviewOpt.get();
			if(!bean.getTitle().isEmpty() && !bean.getContent().isEmpty()) {
				review.setTitle(bean.getTitle());
				review.setContent(bean.getContent());
				review.setRating(bean.getRating());
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
