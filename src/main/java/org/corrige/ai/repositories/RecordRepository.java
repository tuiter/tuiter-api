package org.corrige.ai.repositories;

import java.util.Optional;

import org.corrige.ai.models.record.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends MongoRepository<Record, String> {
	Optional<Record> findByEssayId(String id);
}
