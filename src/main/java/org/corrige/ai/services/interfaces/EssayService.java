package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.essay.EditEssayBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface EssayService {
	Essay create(EssayBean bean) throws UserNotExistsException;
	Essay delete(String id) throws EssayNotExistsException;
	Collection<Essay> findAllByUserUsername(String username) throws UserNotExistsException;
	Collection<Essay> findAllByUserId(String id) throws UserNotExistsException;
	Collection<Essay> findAll();
	Optional<Essay> findByTitleAndUserId(String title, String userId);
	Essay findById(String id) throws EssayNotExistsException;
	Essay update(String id, EditEssayBean bean) throws EssayNotExistsException, EmptyFieldsException;
	EssayToReviewResponse getEssayToReview(String id) throws EssayNotExistsException, UserNotExistsException;
	Collection<EssaysReviews> getEssaysReviews(String id) throws EssayNotExistsException, UserNotExistsException;
}
