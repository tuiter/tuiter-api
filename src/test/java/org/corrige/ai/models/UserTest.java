package org.corrige.ai.models;


import org.corrige.ai.enums.Role;
import org.corrige.ai.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User user1;
	User user2;
	User user3;
	
	@Before
	public void createUsers () {
		user1 = new User("joaoc@email.com", "joaoc", "João Clóvis", "123456", Role.FREE_STUDENT);
		user2 = new User("mariaa@email.com", "mariaa", "Maria Araújo", "543543543", Role.FREE_STUDENT);
		user3 = new User("alex@email.com", "alex", "Alex Garibaldi", "dilma123", Role.FREE_STUDENT);
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
		Assert.assertEquals(user1, new User("joaoc@email.com", "joaoc",  "João Clóvis", "123456", Role.FREE_STUDENT));
		Assert.assertEquals(user1, new User("joaoc@email.com", "joaoc", "João Clóvis", "123456", Role.FREE_STUDENT));
		Assert.assertEquals(user2, new User("mariaa@email.com", "mariaa", "Maria Araújo", "543543543", Role.FREE_STUDENT));
		Assert.assertNotEquals(user1, user2);
		Assert.assertNotEquals(user1, new User("jojo", "jojo@email.com", "123456", "Josefina", Role.FREE_STUDENT));
		Assert.assertNotEquals(user2, new User("auaua", "auaua@email.com", "090909", "Auricele", Role.FREE_STUDENT));
	}
}