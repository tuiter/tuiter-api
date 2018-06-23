package org.corrige.ai.models.user;

import org.corrige.ai.enums.Gender;
import org.corrige.ai.validations.annotations.ValidGender;
import org.corrige.ai.validations.annotations.ValidName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EditUserBean {
	private String requester;
	
	@ValidGender
	private Gender gender;
	@ValidName
	private String name;
	private String photoUrl;
	
	public EditUserBean(String requester, String name, String photo_url, Gender gender) {
		this.requester = requester;
		this.name = name;
		this.photoUrl = photo_url;
		this.gender = gender;
	}

	public EditUserBean(String requester, String name, String photo_url) {
		this.requester = requester;
		this.name = name;
		this.photoUrl = photo_url;
	}

	public EditUserBean(String requester, String name) {
		this(requester, name, "");
	}
}