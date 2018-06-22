package org.corrige.ai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.corrige.ai.models.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
	public Iterable<Review> findAllByUserId(String id);
	public Iterable<Review> findAllByEssayId(String id);
}
