package org.tuiter.util;

import org.tuiter.beans.SignupBean;
import org.tuiter.models.User;

public class Bean2ModelFactory {
	public static User createUser(SignupBean bean) {
		
		return new User(bean.getEmail(), 
						bean.getUsername(), 
						bean.getGender(), 
						bean.getName(), 
						bean.getPassword(), 
						bean.getPhotoUrl());
	}
}
