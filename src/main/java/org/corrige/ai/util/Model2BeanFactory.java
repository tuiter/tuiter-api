package org.corrige.ai.util;

import org.corrige.ai.models.user.User;
import org.corrige.ai.models.user.UserBean;

public class Model2BeanFactory {
	public static UserBean createUserBean(User user) { 
		return new UserBean(user.getUsername(), 
							user.getEmail(),
							user.getName(),
							user.getPhotoUrl(),
							user.getGender());
	}
}
