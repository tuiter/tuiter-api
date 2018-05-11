package org.tuiter.services.interfaces;

import org.tuiter.models.User;

public interface UserService {
	public Iterable<User> findAll();
	public User findByEmail(String email);
	public User findById(String id);
	public User findByUsername(String username);
	public User save(User user);
	public User delete(String id);
	public User update(User user);
}
