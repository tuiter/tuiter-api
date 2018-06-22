package org.corrige.ai.repositories;

import org.corrige.ai.models.essay.Essay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EssayRepository extends MongoRepository<Essay, String> {
	
	public Iterable<Essay> findAllByUserId(String id);

	public Essay findByTitleAndUserId(String title, String id);

}
