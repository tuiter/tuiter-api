package org.tuiter.models;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tuiter.beans.modelbeans.UserBean;
import org.tuiter.util.Gender;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	@Indexed(unique = true)
	private String username;
	@Indexed(unique = true)
	private String email;
	private String photo_url;
	private List<User> followers;
	private List<User> following;
	private String name;
	private String password;
	private Gender gender;
	
	public User() {}
	
	public User(String username, String email, String name, String password, Gender gender) {
		this.username = username;
		this.email = email;
		this.photo_url = "";
		this.name = name;
		this.password = password;
		followers = new ArrayList<>();
		following = new ArrayList<>();
		this.gender = gender;
	}
	
	public User(String username, String email, String photo_url, String name, String password, Gender gender) {
		this.username = username;
		this.email = email;
		this.photo_url = photo_url;
		this.name = name;
		this.password = password;
		followers = new ArrayList<>();
		following = new ArrayList<>();
	}
	
	public User(String username, String email, String name, String password) {
		this.username = username;
		this.email = email;
		this.photo_url = "";
		this.name = name;
		this.password = password;
		followers = new ArrayList<>();
		following = new ArrayList<>();
	}

	public UserBean createBean(){
		return new UserBean(username, email, name, photo_url, followers, following, gender);
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof User)){
			return false;
		}
		User aux = (User) obj;
		if(username.equals(aux.getUsername()) && password.equals(aux.getPassword()) && 
				email.equals(aux.getEmail()) && gender.equals(aux.gender)){
			return true;
		} else {
			return false;
		}
	}
}
