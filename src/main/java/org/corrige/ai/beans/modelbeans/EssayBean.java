package org.corrige.ai.beans.modelbeans;

import org.corrige.ai.util.Type;

public class EssayBean {
	private String userUsername;
	private String title;
	private String theme;
	private String content;
	private Type type;
	
	public EssayBean() {
		
	}

	public EssayBean(String userUsername, String title, String theme, String content, Type type) {
		super();
		this.userUsername = userUsername;
		this.title = title;
		this.theme = theme;
		this.content = content;
		this.type = type;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}	

}
