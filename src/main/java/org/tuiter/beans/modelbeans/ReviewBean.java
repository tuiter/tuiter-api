package org.tuiter.beans.modelbeans;

public class ReviewBean {
	private String essayId;
	private String reviewingUserId;
	
	private String title;
	private String content;
	private int rating;
	
	public ReviewBean() {
		
	}
	
	public ReviewBean(String essayId, String reviewingUserId, String title, String content, int rating) {
		this.essayId = essayId;
		this.reviewingUserId = reviewingUserId;
		this.title = title;
		this.content = content;
		this.rating = rating;
	}

	public String getEssayId() {
		return essayId;
	}

	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}

	public String getReviewingUserId() {
		return reviewingUserId;
	}

	public void setReviewingUserId(String reviewingUserId) {
		this.reviewingUserId = reviewingUserId;
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
