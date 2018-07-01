package org.corrige.ai.models.rating;

import org.corrige.ai.util.Vote;

public class EditRatingBean {
	private Vote vote;
	private String comment;
	
	public EditRatingBean() {}

	public EditRatingBean(Vote vote, String comment) {
		this.vote = vote;
		this.comment = comment;
	}
	
	public EditRatingBean(Vote vote) {
		this.vote = vote;
		this.comment = "";
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