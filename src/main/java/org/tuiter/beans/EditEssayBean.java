package org.tuiter.beans;

public class EditEssayBean {
	private String title;
	private String theme;
	private String content;

	public EditEssayBean() {
		
	}

	public EditEssayBean(String id, String title, String theme, String content) {
		this.title = title;
		this.theme = theme;
		this.content = content;
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
