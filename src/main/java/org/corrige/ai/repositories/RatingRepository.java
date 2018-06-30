package org.corrige.ai.repositories;

import org.corrige.ai.models.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String>{
	
	public Iterable<Rating> findAllByReviewId(String id);

}
