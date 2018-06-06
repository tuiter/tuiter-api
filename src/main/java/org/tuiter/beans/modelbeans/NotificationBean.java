package org.tuiter.beans.modelbeans;

public class NotificationBean {
	private String userId;
	private String reviewId;
	private String description;

	public NotificationBean() {
		
	}

	public NotificationBean(String userId, String reviewId, String description) {
		this.userId = userId;
		this.reviewId = reviewId;
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
}
