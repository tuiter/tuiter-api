package org.corrige.ai.models.user;


import org.corrige.ai.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "users")
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
	
	public User() {
		
	}

	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean authenticate(String password) {
		return this.password.equals(password);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", gender=" + gender + ", name="
				+ name + ", password=" + password + ", photoUrl=" + photoUrl + "]";
	}

	
}
