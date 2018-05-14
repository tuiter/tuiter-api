package org.tuiter.util;

import org.tuiter.beans.modelbeans.UserBean;
import org.tuiter.models.User;

public class Model2BeanFactory {
	public static UserBean createUserBean(User user) { 
		return new UserBean(user.getUsername(), 
							user.getEmail(),
							user.getName(),
							user.getPhotoUrl(),
							user.getGender());
	}
}
