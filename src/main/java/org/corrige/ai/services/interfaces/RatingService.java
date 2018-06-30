package org.corrige.ai.services.interfaces;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.ReviewNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;

public interface RatingService {
	public Rating create(RatingBean rating) throws UserNotFoundException, ReviewNotExistsException, EmptyFieldsException;
	public Rating update(String id, EditRatingBean editRatingBean) throws RatingNotExistsException, EmptyFieldsException;
	public Rating delete(String id) throws RatingNotExistsException;
	public Iterable<Rating> findAllUsersRatings(String userId) throws ReviewNotExistsException, UserNotExistsException, UserNotFoundException;
	public Rating findById(String id) throws RatingNotExistsException;

}
