package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.review.ReviewBean;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface ReviewService {
	Review create(ReviewBean bean) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException;
	Review create(String userId, String essayId) throws UserNotExistsException, UserNotFoundException, EssayNotExistsException;
	Review delete(String id) throws ReviewNotExistsException;
	Collection<Review> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException;
	Collection<Review> findAllByEssayId(String id) throws EssayNotExistsException;
	Collection<Review> findAll();
	Review findById(String id) throws ReviewNotExistsException;
	Review update(String id, EditReviewBean bean) throws ReviewNotExistsException, EmptyFieldsException, EssayNotExistsException;
}
