package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tuiter.beans.EditUserBean;
import org.tuiter.beans.ResetPasswordBean;
import org.tuiter.beans.SignupBean;
import org.tuiter.errors.ErrorCode;
import org.tuiter.errors.exceptions.IncorretPasswordException;
import org.tuiter.errors.exceptions.TuiterApiException;
import org.tuiter.errors.exceptions.UserAlreadyExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.User;
import org.tuiter.repositories.UserRepository;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.Bean2ModelFactory;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository repository){
		this.userRepository = repository;
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(String id) throws UserNotFoundException {
		Optional <User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByIdentifier(String identifier) {
		User user = this.userRepository.findAll().stream().filter(tempUser -> tempUser.getEmail().equals(identifier) 
				|| tempUser.getUsername().equals(identifier)).findFirst().orElse(null);
		
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(String username) throws UserNotFoundException {
		User deletedUser = findByUsername(username);
		if (deletedUser != null) {
			userRepository.deleteById(deletedUser.getId());
			return deletedUser;
		}
		throw new UserNotFoundException();
	}

	private boolean exists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
	public User update(String id, EditUserBean body) throws UserNotFoundException {
		User user = userRepository.findById(id).orElse(null);
		
		if(user != null){
			if (!body.getName().isEmpty()) {
				user.setName(body.getName());
			}
			if (!body.getPhotoUrl().isEmpty()) {
				user.setPhotoUrl(body.getPhotoUrl());
			}
			user.setGender(body.getGender());
			user = userRepository.save(user);
			return user;
		}
		throw new UserNotFoundException();
	}

	@Override
	public User deleteById(String id) throws UserNotFoundException {
		User deletedUser = findById(id);
		if (deletedUser != null) {
			userRepository.deleteById(deletedUser.getId());
			return deletedUser;
		}
		throw new UserNotFoundException();
	}

	@Override
	public User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, IncorretPasswordException {
		User user = findById(id);
		if (user == null) {
			throw new UserNotFoundException();			
		}
		if (!user.getPassword().equals(body.getOldPassword())) {
			throw new IncorretPasswordException();
		}
		
		if (body.getNewPassword().isEmpty()) {
			throw new TuiterApiException("Password is empty!", HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INCORRECT_PASSWORD);
		}
		
		user.setPassword(body.getNewPassword());
		userRepository.save(user);
		return user;
	}

	@Override
	public User create(SignupBean body) throws UserAlreadyExistsException, IncorretPasswordException {
		User user = Bean2ModelFactory.createUser(body);
		if (!(exists(user.getEmail()))) {
			return save(user);
		} else {
			throw new UserAlreadyExistsException();
		}
	}
}
