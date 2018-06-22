package org.corrige.ai.repositories;

import org.corrige.ai.models.review.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
	public Iterable<Review> findAllByUserId(String id);
	public Iterable<Review> findAllByEssayId(String id);
}
