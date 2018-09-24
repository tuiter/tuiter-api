
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.corrige.ai.enums.Type;
import org.corrige.ai.models.essay.Essay;
import org.corrige.ai.models.review.Review;
import org.corrige.ai.models.user.User;

class ReviewTest {
	User user1;
	Review review1;
	
	@Before
	public void createUsersEssaysAndReviews () {
		user1 = new User("1", "Ze", "Jose", "123456", "url");
		review1 = new Review ("1", "1");
	}
	
}
