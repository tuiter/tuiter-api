package org.corrige.ai.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.corrige.ai.beans.ResetPasswordBean;
import org.corrige.ai.beans.SignupBean;
import org.corrige.ai.errors.exceptions.EmptyFieldsException;
import org.corrige.ai.errors.exceptions.IncorretPasswordException;
import org.corrige.ai.errors.exceptions.UserAlreadyExistsException;
import org.corrige.ai.errors.exceptions.UserNotFoundException;
import org.corrige.ai.models.User;
import org.corrige.ai.repositories.UserRepository;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.util.Bean2ModelFactory;

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
		User user = this.userRepository.findAll().stream().filter(
				tempUser -> tempUser.getEmail().equals(identifier) || tempUser.getUsername().equals(identifier))
				.findFirst().orElse(null);
		
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(String id) throws UserNotFoundException {
		User deletedUser = findById(id);
		if (deletedUser != null) {
			userRepository.deleteById(id);
			return deletedUser;
		}
		throw new UserNotFoundException();
	}

	private boolean exists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
	public User update(String id, User body) throws UserNotFoundException {
		User user = userRepository.findById(id).orElse(null);
		
		if(user != null){
			if (!body.getName().isEmpty()) {
				user.setName(body.getName());
			}
			if (body.getPhotoUrl() != null && !body.getPhotoUrl().isEmpty()) {
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
	public User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, 
	IncorretPasswordException, EmptyFieldsException {
		User user = findById(id);
		if (user == null) {
			throw new UserNotFoundException();			
		}
		if (!user.getPassword().equals(body.getOldPassword())) {
			throw new IncorretPasswordException();
		}
		
		if (body.getNewPassword().isEmpty()) {
			throw new EmptyFieldsException("Password is empty!");
		}
		
		user.setPassword(body.getNewPassword());
		userRepository.save(user);
		return user;
	}

	@Override
	public User create(SignupBean body) throws UserAlreadyExistsException {
		User user = Bean2ModelFactory.createUser(body);
		if (!exists(user.getEmail()) && userRepository.findByUsername(user.getUsername()) == null) {
			return save(user);
		} else {
			throw new UserAlreadyExistsException();
		}
	}
}
