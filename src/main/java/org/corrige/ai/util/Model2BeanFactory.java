package org.corrige.ai.util;

import org.corrige.ai.beans.modelbeans.UserBean;
import org.corrige.ai.models.User;

public class Model2BeanFactory {
	public static UserBean createUserBean(User user) { 
		return new UserBean(user.getUsername(), 
							user.getEmail(),
							user.getName(),
							user.getPhotoUrl(),
							user.getGender());
	}
}
