package org.corrige.ai.repositories;

import java.util.Collection;

import org.corrige.ai.models.review.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
	Collection<Review> findAllByUserId(String id);
	Collection<Review> findAllByEssayId(String id);
}
