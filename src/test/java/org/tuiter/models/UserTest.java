package org.tuiter.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tuiter.util.Gender;

public class UserTest {
	User user1;
	User user2;
	User user3;
	
	@Before
	public void createUsers () {
		user1 = new User("joaoc", "joaoc@email.com", Gender.M, "123456", "João Clóvis");
		user2 = new User("mariaa", "mariaa@email.com", Gender.F, "543543543", "Maria Araújo");
		user3 = new User("alex", "alex@email.com", Gender.UNKNOWN, "dilma123", "Alex Garibaldi");
	}
	
	@Test
	public void usernameTest() {
		Assert.assertEquals(user1.getUsername(), "joaoc");
		Assert.assertEquals(user2.getUsername(), "mariaa");
		Assert.assertEquals(user3.getUsername(), "alex");
		
		user1.setUsername("joaocc");
		Assert.assertEquals(user1.getUsername(), "joaocc");
	}
	
	@Test
	public void emailTest() {
		Assert.assertEquals(user1.getEmail(), "joaoc@email.com");
		Assert.assertEquals(user2.getEmail(), "mariaa@email.com");
		Assert.assertEquals(user3.getEmail(), "alex@email.com");
	}
	
	@Test
	public void nameTest() {
		Assert.assertEquals(user1.getName(), "João Clóvis");
		Assert.assertEquals(user2.getName(), "Maria Araújo");
		Assert.assertEquals(user3.getName(), "Alex Garibaldi");
	}
	
	@Test
	public void passwordTest() {
		Assert.assertEquals(user1.getPassword(), "123456");
		Assert.assertEquals(user2.getPassword(), "543543543");
		Assert.assertEquals(user3.getPassword(), "dilma123");
		
		user1.setPassword("lula123");
		Assert.assertEquals(user1.getPassword(), "lula123");
	}
	
	@Test
	public void equalsTest() {
		Assert.assertEquals(user1, new User("joaoc", "joaoc@email.com", Gender.M, "123456", "João Clóvis"));
		Assert.assertEquals(user2, new User("mariaa", "mariaa@email.com", Gender.F, "543543543", "Maria Araújo"));
		Assert.assertNotEquals(user1, user2);
		Assert.assertNotEquals(user1, new User("jojo", "jojo@email.com", Gender.F, "123456", "Josefina"));
		Assert.assertNotEquals(user2, new User("auaua", "auaua@email.com", Gender.F, "090909", "Auricele"));
	}
}