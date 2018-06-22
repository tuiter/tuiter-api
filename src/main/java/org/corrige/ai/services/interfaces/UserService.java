package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface UserService {
	Collection<User> findAll();
	Optional<User> findByEmail(String email);
	Optional<User> findById(String id) throws UserNotFoundException;
	Optional<User> findByUsername(String username);
	User findByIdentifier(String identifier);
	User save(User user);
	Boolean delete(String username) throws UserNotFoundException;
	User deleteById(String username) throws UserNotFoundException;
	User create(SignupBean body) throws UserAlreadyExistsException;
	User update(String id, User body) throws UserNotFoundException;
	User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, IncorretPasswordException, EmptyFieldsException;
}

