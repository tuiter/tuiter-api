package org.corrige.ai.services.interfaces;

import java.util.List;

import org.corrige.ai.beans.EditEssayBean;
import org.corrige.ai.beans.modelbeans.EssayBean;
import org.corrige.ai.beans.EssayToReviewResponse;
import org.corrige.ai.beans.EssaysReviews;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.EssayNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotFoundException;
import org.corrige.ai.models.Essay;
import org.corrige.ai.models.Review;

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
