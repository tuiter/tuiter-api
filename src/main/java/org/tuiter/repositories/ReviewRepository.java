package org.tuiter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tuiter.models.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
	public Iterable<Review> findAllByUserId(String id);
}
