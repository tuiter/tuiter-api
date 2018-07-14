package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.models.user.UserBadges;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

public interface UserService {
	Collection<User> findAll();
	Optional<User> findByEmail(String email);
	User findById(String id) throws UserNotExistsException;
	Optional<User> findByUsername(String username);
	User findByIdentifier(String identifier);
	Boolean existsById(String id);
	User save(User user);
	Boolean delete(String username) throws UserNotExistsException;
	User deleteById(String username) throws UserNotExistsException;
	User create(SignupBean body) throws UserAlreadyExistsException;
	User update(String id, User body) throws UserNotExistsException;
	User resetPassword(String id, ResetPasswordBean body) throws IncorretPasswordException, EmptyFieldsException, UserNotExistsException;
	UserBadges getBadges(String userId) throws UserNotExistsException;
}

