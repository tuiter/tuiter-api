package org.corrige.ai.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
	private String username;
	private String email;
	private String name;
	private String photo_url;
}