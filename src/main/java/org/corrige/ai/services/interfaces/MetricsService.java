package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface MetricsService {

	Collection<Essay> getEvolution(String userId) throws UserNotExistsException;

	void getEssaysStatusSummarised(String userId);

	void getMeanRatingPerRequirement(String userId);

	void getReviewEvaluation(String userId);

	void getReviewEvaluationPerRating(String userId);

}
