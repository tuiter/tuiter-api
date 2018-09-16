package org.corrige.ai.models.user;


import org.corrige.ai.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String username;
	
	private String name;
	@JsonIgnore
	private String password;
	private String photoUrl;
	
	private Role role;
	
	@JsonIgnore
	private float cash;
	
	@Field
	private Boolean usingWeekelyTopic = false;
	
	public User(String email, String username, String name, String password, String photoUrl, Role role) {
		this.email = email;
		this.username = username;
		this.role = role;
		
		this.name = name;
		this.password = password;
		this.photoUrl = photoUrl;
		this.cash = 0;
	}
	
	public User(String email, String username,  String name, String password, Role role) {
		this(email, username, name, password, "", role);
	}
	
	public Boolean authenticate(String password) {
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username +  ", name="
				+ name + ", password=" + password + ", photoUrl=" + photoUrl + "]";
	}

	
}
