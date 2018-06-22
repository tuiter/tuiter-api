package org.corrige.ai.models.review;

import java.util.List;

public class ReviewBean {
	private String essayId;
	private String reviewingUserId;
	
	private List<String> comments;
	private List<Double> ratings;
	
	public ReviewBean() {
		
	}
	
	public ReviewBean(String essayId, String reviewingUserId, List<String> comments, List<Double> ratings) {
		this.essayId = essayId;
		this.reviewingUserId = reviewingUserId;
		this.comments = comments;
		this.ratings = ratings;
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

	public List<String> getComments() {
		return comments;
	}

	public void setContent(List<String> comments) {
		this.comments = comments;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}
}
