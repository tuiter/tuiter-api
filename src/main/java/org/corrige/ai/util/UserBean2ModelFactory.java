package org.corrige.ai.util;

import org.corrige.ai.models.user.SignupBean;
import org.corrige.ai.models.user.User;

public class UserBean2ModelFactory {
	public static User createUser(SignupBean bean) {
		return new User(bean.getEmail(), 
						bean.getUsername(), 
						bean.getGender(), 
						bean.getName(), 
						bean.getPassword(), 
						bean.getPhotoUrl());
	}
}
