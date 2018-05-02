package org.tuiter.beans.modelbeans;

import java.util.List;

import org.tuiter.models.User;

public class UserBean {
	private String username;
	private String email;
	private String name;
	private String photo_url;
	private List<User> followers;
	private List<User> following;
	
	public UserBean() {
		
	}
	
	public UserBean(String username, String email, String name, String photo_url, List<User> followers,
			List<User> following) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.photo_url = photo_url;
		this.followers = followers;
		this.following = following;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}