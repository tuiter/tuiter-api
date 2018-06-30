package org.corrige.ai.models.rating;

import org.corrige.ai.util.Vote;

public class RatingBean {
	private String userId;
	private String reviewId;
	private Vote vote;
	private String comment;
	
	public RatingBean() {}

	public RatingBean(String userId, String reviewId, Vote vote, String comment) {
		this.userId = userId;
		this.reviewId = reviewId;
		this.vote = vote;
		this.comment = comment;
	}
	
	public RatingBean(String userId, String reviewId, Vote vote) {
		this.userId = userId;
		this.reviewId = reviewId;
		this.vote = vote;
		this.comment = "";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}	
}
