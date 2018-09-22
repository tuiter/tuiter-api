package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.services.interfaces.EssayService;
import org.corrige.ai.services.interfaces.MetricsService;
import org.corrige.ai.services.interfaces.RatingService;
import org.corrige.ai.services.interfaces.ReviewService;
import org.corrige.ai.util.Vote;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;

public class MetricsServiceImpl implements MetricsService {
	@Autowired
	private EssayService essayService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private RatingService ratingService;

	@Override
	public Collection<Essay> getEvolution(String userId) throws UserNotExistsException {
		return this.essayService
				.findAllByUserId(userId)
				.stream()
				.sorted(Comparator.comparing(Essay::getCreatedAt))
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
	public void getMeanRatingPerRequirement(String userId) {
		// TODO Auto-generated method stub

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
	public void getReviewEvaluationPerRating(String userId) {
		// TODO Auto-generated method stub

	}

}
