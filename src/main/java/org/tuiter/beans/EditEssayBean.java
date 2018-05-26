package org.tuiter.beans;

import org.tuiter.util.Type;

public class EditEssayBean {
	private String title;
	private String theme;
	private String content;
	private Type type;

	public EditEssayBean() {
		
	}

	public EditEssayBean(String id, String title, String theme, String content, Type type) {
		this.title = title;
		this.theme = theme;
		this.content = content;
		this.type = type;
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
