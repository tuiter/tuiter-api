package org.corrige.ai.repositories;

import java.util.Collection;

import org.corrige.ai.models.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String>{
	
	public Collection<Rating> findAllByReviewId(String id);

}
