package org.corrige.ai.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.repositories.RatingRepository;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
	private RatingRepository ratingRepository;
	private UserService userService;
	private ReviewService reviewService;
	
	@Autowired
	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setReviewService(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}

	@Override
	public Rating create(RatingBean rating) throws UserNotExistsException, 
	ReviewNotExistsException, EmptyFieldsException {
		if (userService.findById(rating.getUserId()) != null) {
			if (reviewService.findById(rating.getReviewId()) != null) {
				if (rating.getVote() != null && rating.getComment() != null) {
					Rating newRating = new Rating(rating.getUserId(), rating.getReviewId(), rating.getVote(),
							rating.getComment());
					return ratingRepository.save(newRating);
				} else {
					throw new EmptyFieldsException();
				}
			} else {
				throw new ReviewNotExistsException();
			}
		}
		throw new UserNotExistsException();
	}

	@Override
	public Rating update(String id, EditRatingBean editRatingBean) throws RatingNotExistsException, EmptyFieldsException {
		Rating rating = findById(id);
		if(rating != null) {
			if (editRatingBean.getVote() != null) {
				rating.setVote(editRatingBean.getVote());
				rating.setComment(editRatingBean.getComment());
				return ratingRepository.save(rating);
			} else {
				throw new EmptyFieldsException();
			}
		}
		throw new RatingNotExistsException();
	}

	@Override
	public Rating delete(String id) throws RatingNotExistsException {
		Rating deletedRating = findById(id);
		if(deletedRating != null) {
			ratingRepository.delete(deletedRating);
			return deletedRating;
		} else {
			throw new RatingNotExistsException();
		}
	}

	@Override
	public List<Rating> findAllUsersRatings(String userId) throws ReviewNotExistsException, UserNotExistsException {
		List<Rating> ratings = new ArrayList<>();
		if (userService.findById(userId) != null) {
			Iterable<Review> reviews = reviewService.findAllByUserId(userId);
			for (Review review: reviews) {
				Iterable<Rating> ratings_it = ratingRepository.findAllByReviewId(review.getId());
				for (Rating rating : ratings_it) {
					ratings.add(rating);
				}
			}
			return ratings;
		}
		throw new UserNotExistsException();
	}

	@Override
	public Rating findById(String id) throws RatingNotExistsException {
		Optional<Rating> ratingOpt = ratingRepository.findById(id);
		if(ratingOpt.isPresent()) {
			return ratingOpt.get();
		}
		throw new RatingNotExistsException();
	}

	@Override
	public Optional<Rating> findByReviewId(String id) {
		return this.ratingRepository.findAllByReviewId(id).stream().findFirst();
	}

}
