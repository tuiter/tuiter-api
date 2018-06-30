package org.corrige.ai.services.interfaces;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface RatingService {
	public Rating create(RatingBean rating) throws ReviewNotExistsException, EmptyFieldsException, UserNotExistsException;
	public Rating update(String id, EditRatingBean editRatingBean) throws RatingNotExistsException, EmptyFieldsException;
	public Rating delete(String id) throws RatingNotExistsException;
	public Iterable<Rating> findAllUsersRatings(String userId) throws ReviewNotExistsException, UserNotExistsException;
	public Rating findById(String id) throws RatingNotExistsException;

}
