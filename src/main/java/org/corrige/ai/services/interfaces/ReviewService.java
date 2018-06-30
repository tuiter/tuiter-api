package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.models.review.EditReviewBean;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface ReviewService {
	Review delete(String id) throws ReviewNotExistsException;
	Collection<Review> findAllByUserId(String id) throws UserNotExistsException;
	Collection<Review> findAllByEssayId(String id) throws EssayNotExistsException;
	Collection<Review> findAll();
	Review findById(String id) throws ReviewNotExistsException;
	Review update(String id, EditReviewBean bean) throws ReviewNotExistsException, EmptyFieldsException, EssayNotExistsException;
	Review create(String id, String id2);
}
