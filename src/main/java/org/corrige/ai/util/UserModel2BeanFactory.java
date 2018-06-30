package org.corrige.ai.util;

import org.corrige.ai.models.user.User;
import org.corrige.ai.models.user.UserBean;

public class UserModel2BeanFactory {
	public static UserBean createUserBean(User user) { 
		return new UserBean(user.getUsername(), 
							user.getEmail(),
							user.getName(),
							user.getPhotoUrl(),
							user.getGender());
	}
}
