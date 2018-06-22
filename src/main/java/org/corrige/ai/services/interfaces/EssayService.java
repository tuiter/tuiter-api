package org.corrige.ai.services.interfaces;

import java.util.List;

import org.corrige.ai.models.essay.EditEssayBean;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.essay.EssayBean;
import org.corrige.ai.models.review.EssayToReviewResponse;
import org.corrige.ai.models.review.EssaysReviews;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface EssayService {
	public Essay create(EssayBean bean) throws UserNotExistsException;
	public Essay delete(String id) throws EssayNotExistsException;
	public Iterable<Essay> findAllByUserUsername(String username) throws UserNotExistsException;
	public Iterable<Essay> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Essay> findAll();
	public Essay findByTitleAndUserId(String title, String userId);
	public Essay findById(String id) throws EssayNotExistsException;
	public Essay update(String id, EditEssayBean bean) throws EssayNotExistsException, EmptyFieldsException;
	public EssayToReviewResponse getEssayToReview(String id) throws EssayNotExistsException, UserNotFoundException, UserNotExistsException;
	public List<EssaysReviews> getEssaysReviews(String id) throws EssayNotExistsException, UserNotFoundException, UserNotExistsException;
}
