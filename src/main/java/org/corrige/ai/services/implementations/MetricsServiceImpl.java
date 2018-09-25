package org.corrige.ai.services.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.corrige.ai.enums.RatingClass;
import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.review.MinimalReview;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.MetricsService;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.Vote;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl implements MetricsService {
	@Autowired
	private EssayService essayService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private RatingService ratingService;

	@Override
	public Collection<Double> getEvolution(String userId) throws UserNotExistsException {
		return this.essayService
			.findAllByUserId(userId)
			.stream()
			.sorted(Essay::compareTo)
			.map(essay -> this.essayService.getEssayRating(essay.getId()))
			.filter(rating -> rating.isPresent())
			.map(evaluation -> evaluation.getAsDouble())
			.collect(Collectors.toList());
	}

	@Override
	public Map<ReviewStatus, Long> getEssaysStatusSummarised(String userId) throws UserNotExistsException {
		return this.essayService
			.findAllByUserId(userId)
			.stream()
			.collect(Collectors.groupingBy(Essay::getStatus, Collectors.counting()));
	}

	@Override
	public Collection<Double> getMeanRatingPerRequirement(String userId) throws UserNotExistsException {
		Collection<Review> reviews = this.reviewService.findAllByUserId(userId);
		
		return reviews
			.stream()
			.map(review -> review.getRatings())
			.reduce((x, y) -> reduceLists(x, y))
			.map(ratings -> getListOfMeans(ratings, reviews.size()))
			.orElse(new ArrayList<>());

	}
	
	private List<Double> reduceLists(List<Double> x, List<Double> y) {
		int i = 0;
		List<Double> response = new ArrayList<>();
		
		for (Double xi : x)
			response.add(xi + y.get(i++));	
		return response;
	}
	
	private List<Double> getListOfMeans(List<Double> sumRatings, int count) {
		return sumRatings
			.stream()
			.map(rating -> rating / count)
			.collect(Collectors.toList());
	}

	@Override
	public Map<Vote, Long> getReviewEvaluation(String userId) throws UserNotExistsException {
		return this.reviewService
			.findAllByUserId(userId)
			.stream()
			.map(review -> review.getId())
			.map(reviewId -> this.ratingService.findByReviewId(reviewId))
			.filter(ratingOP -> ratingOP.isPresent())
			.map(rating -> rating.get())
			.collect(Collectors.groupingBy(Rating::getVote, Collectors.counting()));
		
	}

	@Override
	public Map<RatingClass, Map<Vote, Long>> getReviewEvaluationPerRating(String userId) throws UserNotExistsException {
		return this.reviewService
			.findAllByUserId(userId)
			.stream()
			.map(review -> new MinimalReview(this.ratingService.findByReviewId(review.getId()), review.getRatings()))
			.filter(minReview -> minReview.getVote() != null && minReview.getRating() != null)
			.collect(Collectors.groupingBy(MinimalReview::getRating, 
					 Collectors.groupingBy(MinimalReview::getVote, 
					 Collectors.counting())));
	}

}
