package org.tuiter.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tuiter.util.Gender;

public class EssayTest {
	User user1;
	User user2;
	Essay essay1;
	Essay essay2;
	Essay essay3;
	
	@Before
	public void createUsersAndEssays () {
		user1 = new User("joaoc@email.com", "joaoc", Gender.M, "João Clóvis", "123456");
		user2 = new User("mariaa@email.com", "mariaa", Gender.F, "Maria Araújo", "543543543");
		
		essay1 = new Essay(user1, "Hoje e ontem", "Tempo", "Texto aqui");
		essay2 = new Essay(user2, "Hoje e amanhã", "Tempo", "Outro texto aqui");
		essay3 = new Essay(user1, "Pera e uva", "Comida", "Texto terceiro aqui");
	}
	
	@Test
	public void userTest() {
		Assert.assertEquals(essay1.getUser(), user1);
		Assert.assertEquals(essay2.getUser(), user2);
		Assert.assertEquals(essay3.getUser(), user1);
		
	}
	
	@Test
	public void titleTest() {
		Assert.assertEquals(essay1.getTitle(), "Hoje e ontem");
		Assert.assertEquals(essay2.getTitle(), "Hoje e amanhã");
		Assert.assertEquals(essay3.getTitle(), "Pera e uva");
	}
	
	@Test
	public void themeTest() {
		Assert.assertEquals(essay1.getTheme(), "Tempo");
		Assert.assertEquals(essay2.getTheme(), "Tempo");
		Assert.assertEquals(essay3.getTheme(), "Comida");
	}
	
	@Test
	public void contentTest() {
		Assert.assertEquals(essay1.getContent(), "Texto aqui");
		Assert.assertEquals(essay2.getContent(), "Outro texto aqui");
		Assert.assertEquals(essay3.getContent(), "Texto terceiro aqui");
	}
	
	@Test
	public void equalsTest() {
		Assert.assertEquals(essay1, new Essay(user1, "Hoje e ontem", "Tempo", "Texto aqui"));
		Assert.assertEquals(essay2, new Essay(user2, "Hoje e amanhã", "Tempo", "Outro texto aqui"));
		Assert.assertEquals(essay3, new Essay(user1, "Pera e uva", "Comida", "Texto terceiro aqui"));
		Assert.assertNotEquals(essay1, essay3);
	}

}
