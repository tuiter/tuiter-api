package org.tuiter.beans.modelbeans;

public class EssayBean {
	private String userUsername;
	private String title;
	private String theme;
	private String content;
	
	public EssayBean() {
		
	}
	
	public EssayBean(String userUsername, String title, String theme, String content) {
		super();
		this.userUsername = userUsername;
		this.title = title;
		this.theme = theme;
		this.content = content;
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
	

}
