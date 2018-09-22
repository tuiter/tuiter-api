package org.corrige.ai.services.interfaces;

import java.util.Optional;

import org.corrige.ai.models.rating.EditRatingBean;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.models.rating.RatingBean;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface RatingService {
	Rating create(RatingBean rating) throws ReviewNotExistsException, EmptyFieldsException, UserNotExistsException;
	Rating update(String id, EditRatingBean editRatingBean) throws RatingNotExistsException, EmptyFieldsException;
	Rating delete(String id) throws RatingNotExistsException;
	Iterable<Rating> findAllUsersRatings(String userId) throws ReviewNotExistsException, UserNotExistsException;
	Rating findById(String id) throws RatingNotExistsException;
	Optional<Rating> findByReviewId(String id);
}
