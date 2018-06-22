package org.corrige.ai.beans;

public class Essay {
	String title;
	String theme;
	
	public Essay() {}
	
	public Essay(String title, String theme) {
		this.title = title;
		this.theme = theme;
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
}
