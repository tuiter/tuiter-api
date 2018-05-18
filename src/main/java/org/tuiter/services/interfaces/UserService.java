package org.tuiter.services.interfaces;

import org.tuiter.beans.EditUserBean;
import org.tuiter.beans.ResetPasswordBean;
import org.tuiter.beans.SignupBean;
import org.tuiter.errors.exceptions.IncorretPasswordException;
import org.tuiter.errors.exceptions.UserAlreadyExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.User;

public interface UserService {
	public Iterable<User> findAll();
	public User findByEmail(String email);
	public User findById(String id) throws UserNotFoundException;
	public User findByUsername(String username) throws UserNotFoundException;
	public User save(User user);
	public User delete(String username) throws UserNotFoundException;
	public User deleteById(String username) throws UserNotFoundException;
	public User create(SignupBean body) throws UserAlreadyExistsException, IncorretPasswordException;
	public User update(EditUserBean body) throws UserNotFoundException;
	public User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, IncorretPasswordException;
}

