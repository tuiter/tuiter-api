package org.corrige.ai.models.essay;

public class SimpleEssayBean {
	private String title;
	private String theme;
	
	public SimpleEssayBean() {}
	
	public SimpleEssayBean(String title, String theme) {
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
