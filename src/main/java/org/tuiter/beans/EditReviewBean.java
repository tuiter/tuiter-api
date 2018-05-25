package org.tuiter.beans;

public class EditReviewBean {
	private String title;
	private String content;
	private int rating;

	public EditReviewBean() {
		
	}

	public EditReviewBean(String title, String content, int rating) {
		this.title = title;
		this.content = content;
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}	
}
