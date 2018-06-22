package org.corrige.ai.models.user;

import org.corrige.ai.enums.Gender;
import org.corrige.ai.validations.annotations.ValidGender;
import org.corrige.ai.validations.annotations.ValidName;

public class EditUserBean {
	private String requester;
	
	@ValidGender
	private Gender gender;
	@ValidName
	private String name;
	private String photoUrl;
	
	public EditUserBean() {}
	
	public EditUserBean(String requester, String name, String photo_url, Gender gender) {
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