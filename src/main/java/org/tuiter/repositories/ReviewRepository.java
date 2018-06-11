package org.tuiter.repositories;

import org.tuiter.models.Review;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	public Collection<Review> findAllByUserId(Long id);
	public Collection<Review> findAllByEssayId(Long id);
}
