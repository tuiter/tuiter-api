package org.corrige.ai.services.interfaces;

import org.corrige.ai.beans.ResetPasswordBean;
import org.corrige.ai.beans.SignupBean;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.IncorretPasswordException;
import org.corrige.ai.errors.exceptions.UserAlreadyExistsException;
import org.corrige.ai.errors.exceptions.UserNotFoundException;
import org.corrige.ai.models.User;

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

