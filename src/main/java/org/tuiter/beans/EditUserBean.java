package org.tuiter.beans;

import org.tuiter.errors.annotations.ValidGender;
import org.tuiter.errors.annotations.ValidName;
import org.tuiter.util.Gender;

public class EditUserBean {
	private String requester;
	
	@ValidGender
	private Gender gender;
	@ValidName
	private String name;
	private String photoUrl;
	
	public EditUserBean() {
		
	}
	
	public EditUserBean(String requester, String name, String photo_url, Gender gender) {
		super();
		this.requester = requester;
		this.name = name;
		this.photoUrl = photo_url;
		this.gender = gender;
	}

	public EditUserBean(String requester, String name, String photo_url) {
		super();
		this.requester = requester;
		this.name = name;
		this.photoUrl = photo_url;
	}

	public EditUserBean(String requester, String name) {
		this(requester, name, "");
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
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
	
	public Gender getGender() {
		return gender;
	}
 
public void setGender(Gender gender) {
		this.gender = gender;
	}
}