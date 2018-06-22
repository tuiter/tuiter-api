package org.corrige.ai.services.interfaces;

import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.review.ReviewBean;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

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
