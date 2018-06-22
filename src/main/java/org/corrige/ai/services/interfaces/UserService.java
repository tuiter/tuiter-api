package org.corrige.ai.services.interfaces;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;

public interface UserService {
	public Iterable<User> findAll();
	public User findByEmail(String email);
	public User findById(String id) throws UserNotFoundException;
	public User findByUsername(String username);
	public User findByIdentifier(String identifier);
	public User save(User user);
	public User delete(String username) throws UserNotFoundException;
	public User deleteById(String username) throws UserNotFoundException;
	public User create(SignupBean body) throws UserAlreadyExistsException;
	public User update(String id, User body) throws UserNotFoundException;
	public User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, IncorretPasswordException, EmptyFieldsException;
}

