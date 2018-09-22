package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Map;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface MetricsService {

	Collection<Essay> getEvolution(String userId) throws UserNotExistsException;

	Map<ReviewStatus, Long> getEssaysStatusSummarised(String userId) throws UserNotExistsException;

	void getMeanRatingPerRequirement(String userId);

	void getReviewEvaluation(String userId);

	void getReviewEvaluationPerRating(String userId);

}
