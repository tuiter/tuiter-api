package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Map;

import org.corrige.ai.enums.RatingClass;
import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.util.Vote;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface MetricsService {

	Collection<Essay> getEvolution(String userId) throws UserNotExistsException;

	Map<ReviewStatus, Long> getEssaysStatusSummarised(String userId) throws UserNotExistsException;

	Collection<Double> getMeanRatingPerRequirement(String userId) throws UserNotExistsException;

	Map<Vote, Long> getReviewEvaluation(String userId) throws UserNotExistsException;

	Map<RatingClass, Map<Vote, Long>> getReviewEvaluationPerRating(String userId) throws UserNotExistsException;

}
