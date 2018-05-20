package org.tuiter.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tuiter.models.Essay;

public interface EssayRepository extends MongoRepository<Essay, String> {
	
	public Iterable<Essay> findAllByUserId(String id);

	public Essay findByTitleAndUserId(String title, String id);

}
