package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.models.User;
import org.tuiter.repositories.UserRepository;
import org.tuiter.services.interfaces.UserService;

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
	public User findById(String id) {
		Optional <User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(String id) {
		User deletedUser = findById(id);
		userRepository.deleteById(id);
		return deletedUser;
	}

	@Override
	public User update(User user) {
		User result = null;
		
		if(userRepository.findById(user.getId()) != null){
			result = userRepository.save(user);
		}
		return result;
	}

}
