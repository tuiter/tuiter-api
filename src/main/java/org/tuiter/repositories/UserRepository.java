package org.tuiter.repositories;

import org.tuiter.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);

	public User findByUsername(String username);
	
}