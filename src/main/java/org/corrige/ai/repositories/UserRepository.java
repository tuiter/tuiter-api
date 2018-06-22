package org.corrige.ai.repositories;

import java.util.Optional;

import org.corrige.ai.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
}