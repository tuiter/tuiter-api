package org.corrige.ai.models.user;


import org.corrige.ai.enums.Gender;
import org.corrige.ai.models.notification.Notification;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
	
	private Gender gender;
	private String name;
	@JsonIgnore
	private String password;
	private String photoUrl;
	
	public User(String email, String username, Gender gender, String name, String password, String photo_url) {
		this.email = email;
		this.username = username;
		
		this.gender = gender;
		this.name = name;
		this.password = password;
		this.photoUrl = photo_url;
	}
	
	public User(String email, String username, Gender gender,  String name, String password) {
		this(email, username, gender, name, password, "");
	}
	
	public User(String email, String username, String name, String password) {
		this(email, username, Gender.UNKNOWN, name, password, "");
	}
	
	public Boolean authenticate(String password) {
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", gender=" + gender + ", name="
				+ name + ", password=" + password + ", photoUrl=" + photoUrl + "]";
	}

	
}
