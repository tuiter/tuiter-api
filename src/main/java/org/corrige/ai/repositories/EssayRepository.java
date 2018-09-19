package org.corrige.ai.repositories;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.essay.Essay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EssayRepository extends MongoRepository<Essay, String> {
	Collection<Essay> findAllByUserId(String id);
	Optional<Essay> findByTitleAndUserId(String title, String id);
	Collection<Essay> findByPremium(Boolean premium);
}
