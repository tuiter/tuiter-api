package org.tuiter.services.interfaces;

import org.tuiter.beans.EditEssayBean;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.beans.EssayToReviewResponse;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;

public interface EssayService {
	public Essay create(EssayBean bean) throws UserNotExistsException;
	public Essay delete(String id) throws EssayNotExistsException;
	public Iterable<Essay> findAllByUserUsername(String username) throws UserNotExistsException;
	public Iterable<Essay> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Essay> findAll();
	public Essay findByTitleAndUserId(String title, String userId);
	public Essay findById(Integer id) throws EssayNotExistsException;
	public Essay update(Integer id, EditEssayBean bean) throws EssayNotExistsException, EmptyFieldsException;
	public EssayToReviewResponse getEssayToReview(String id) throws EssayNotExistsException, UserNotFoundException, UserNotExistsException;
}
