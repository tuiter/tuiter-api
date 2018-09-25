package org.corrige.ai.services;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.repositories.UserRepository;
import org.corrige.ai.services.implementations.UserServiceImpl;
import org.corrige.ai.validations.exceptions.UserNotExistsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserServiceImpl service;
	
	SignupBean bean1 = new SignupBean("user1@gmail.com", "User1", "pass123", "photo", "user1", null);
	SignupBean bean2 = new SignupBean("user2@gmail.com", "User2", "pass123", "photo", "user2", null);
	SignupBean bean3 = new SignupBean("user3@gmail.com", "User3", "pass123", "photo", "user3", null);
	
	User user1 = new User(bean1.getEmail(), bean1.getUsername(), bean1.getName(), bean1.getPassword(), bean1.getPhotoUrl(), null);
	User user2 = new User(bean2.getEmail(), bean2.getUsername(), bean2.getName(), bean2.getPassword(), bean2.getPhotoUrl(), null);
	User user3 = new User(bean3.getEmail(), bean3.getUsername(), bean3.getName(), bean3.getPassword(), bean3.getPhotoUrl(), null);

	Optional<User> opt_user1 = Optional.of(user1);
	Optional<User> opt_user2 = Optional.of(user2);
	Optional<User> opt_user3 = Optional.of(user3);
	
	@Test
	public void findUserTests() {
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		when(userRepository.findAll()).thenReturn(users);
		Assert.assertEquals(this.service.findAll().size(), 3);
		
		
		when(userRepository.findByEmail(user1.getEmail())).thenReturn(opt_user1);
		when(userRepository.findByEmail(user2.getEmail())).thenReturn(opt_user2);
		when(userRepository.findByEmail(user3.getEmail())).thenReturn(opt_user3);
		
		Assert.assertEquals(this.service.findByEmail(user1.getEmail()), opt_user1);
		Assert.assertEquals(this.service.findByEmail(user2.getEmail()), opt_user2);
		Assert.assertEquals(this.service.findByEmail(user3.getEmail()), opt_user3);
		
		
		when(userRepository.findById("1")).thenReturn(opt_user1);
		when(userRepository.findById("2")).thenReturn(opt_user2);
		when(userRepository.findById("3")).thenReturn(opt_user3);
		when(userRepository.findById("4")).thenReturn(Optional.ofNullable(null));
		Assert.assertTrue(this.service.existsById("1"));
		Assert.assertTrue(this.service.existsById("2"));
		Assert.assertTrue(this.service.existsById("3"));
		Assert.assertFalse(this.service.existsById("4"));
		
		
		when(userRepository.findByUsername(user1.getUsername())).thenReturn(opt_user1);
		when(userRepository.findByUsername(user2.getUsername())).thenReturn(opt_user2);
		when(userRepository.findByUsername(user3.getUsername())).thenReturn(opt_user3);
		when(userRepository.findByUsername("hadrizia")).thenReturn(null);
		Assert.assertEquals(this.service.findByUsername(user1.getUsername()), opt_user1);
		Assert.assertEquals(this.service.findByUsername(user2.getUsername()), opt_user2);
		Assert.assertEquals(this.service.findByUsername(user3.getUsername()), opt_user3);
		Assert.assertEquals(this.service.findByUsername("hadrizia"), null);
		
		Assert.assertEquals(this.service.findByIdentifier(user1.getUsername()), user1);
		Assert.assertEquals(this.service.findByIdentifier(user2.getEmail()), user2);
		Assert.assertEquals(this.service.findByIdentifier(user3.getEmail()), user3);
	}
	@Test
	public void saveUserTest() {
		when(userRepository.save(user1)).thenReturn(user1);
		when(userRepository.save(user2)).thenReturn(user2);
		when(userRepository.save(user3)).thenReturn(user3);
		
		Assert.assertEquals(this.service.save(user1), user1);
		Assert.assertEquals(this.service.save(user2), user2);
		Assert.assertEquals(this.service.save(user3), user3);
		
		
	}
	
	@Test
	public void updateUserTest() {
		user1.setName("New User1");
		user2.setEmail("newuser2@gmail.com");
		user3.setUsername("New username3");
		
		when(userRepository.findById("1")).thenReturn(opt_user1);
		when(userRepository.findById("2")).thenReturn(opt_user2);
		when(userRepository.findById("3")).thenReturn(opt_user3);
		
		when(userRepository.save(user1)).thenReturn(user1);
		when(userRepository.save(user2)).thenReturn(user2);
		when(userRepository.save(user3)).thenReturn(user3);
		
		when(userRepository.findById("4")).thenReturn(null);
		
		try {
			Assert.assertEquals(this.service.update("1", user1), user1);
			Assert.assertEquals(this.service.update("2", user2), user2);
			Assert.assertEquals(this.service.update("3", user3), user3);
		} catch (UserNotExistsException e) {
			e.printStackTrace();
		}
		
	}
	
}
