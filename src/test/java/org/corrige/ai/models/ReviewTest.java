package org.corrige.ai.models;


import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.user.User;
import org.junit.Before;

class ReviewTest {
	User user1;
	Review review1;
	
	@Before
	public void createUsersEssaysAndReviews () {
		user1 = new User("1", "Ze", "Jose", "123456", "url", null);
		review1 = new Review ("1", "1");
	}
	
}
