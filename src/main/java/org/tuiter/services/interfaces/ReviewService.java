package org.tuiter.services.interfaces;

import org.tuiter.beans.EditReviewBean;
import org.tuiter.beans.modelbeans.ReviewBean;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Review;

public interface ReviewService {
	public Review create(ReviewBean bean) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException;
	public Review create(String userId, String essayId) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException;
	public Review delete(String id) throws ReviewNotExistsException;
	public Iterable<Review> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	public Iterable<Review> findAllByEssayId(String id) throws EssayNotExistsException;
	public Iterable<Review> findAll();
	public Review findById(String id) throws ReviewNotExistsException;
	public Review update(String id, EditReviewBean bean) throws ReviewNotExistsException, EmptyFieldsException, EssayNotExistsException;
}
