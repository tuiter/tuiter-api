package org.corrige.ai.services.interfaces;

import org.corrige.ai.beans.EditReviewBean;
import org.corrige.ai.beans.modelbeans.ReviewBean;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.EssayNotExistsException;
import org.corrige.ai.errors.exceptions.ReviewNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotExistsException;
import org.corrige.ai.errors.exceptions.UserNotFoundException;
import org.corrige.ai.models.Review;

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
