package org.corrige.ai.util;

import org.corrige.ai.beans.SignupBean;
import org.corrige.ai.models.User;

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
