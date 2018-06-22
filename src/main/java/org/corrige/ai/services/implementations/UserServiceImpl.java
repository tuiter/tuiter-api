package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.auth.ResetPasswordBean;
import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.UserRepository;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.util.UserBean2ModelFactory;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	private Boolean existsById(String id) {
		Optional <User> user = userRepository.findById(id);
		return user.isPresent();
	}

	@Override
	public Optional<User> findByUsername(String username) {
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
	public Boolean delete(String id) throws UserNotFoundException {
		if (existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		throw new UserNotFoundException();
	}

	private boolean existsByEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
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
		Optional<User> deletedUser = this.userRepository.findById(id);
		if (deletedUser != null) {
			userRepository.deleteById(deletedUser.get().getId());
			return deletedUser.get();
		}
		throw new UserNotFoundException();
	}

	@Override
	public User resetPassword(String id, ResetPasswordBean body) throws UserNotFoundException, 
	IncorretPasswordException, EmptyFieldsException {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			throw new UserNotFoundException();			
		}
		if (!user.get().getPassword().equals(body.getOldPassword())) {
			throw new IncorretPasswordException();
		}
		
		if (body.getNewPassword().isEmpty()) {
			throw new EmptyFieldsException("Password is empty!");
		}
		
		user.get().setPassword(body.getNewPassword());
		return userRepository.save(user.get());
	}

	@Override
	public User create(SignupBean body) throws UserAlreadyExistsException {
		User user = UserBean2ModelFactory.createUser(body);
		if (!existsByEmail(user.getEmail()) && userRepository.findByUsername(user.getUsername()) == null) {
			return save(user);
		} else {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public Optional<User> findById(String id) throws UserNotFoundException {
		return this.userRepository.findById(id);
	}
}
