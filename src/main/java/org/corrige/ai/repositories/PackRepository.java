package org.corrige.ai.repositories;

import java.util.Collection;

import org.corrige.ai.models.pack.Pack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends MongoRepository<Pack, String>{
	Collection<Pack> findAllByUserId(String userId);
}
