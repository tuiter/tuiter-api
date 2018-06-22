package org.corrige.ai.beans;

import java.util.List;

public class EditReviewBean {
	
	private List<String> comments;
	private List<Double> ratings;

	public EditReviewBean() {
		
	}

	public EditReviewBean( List<String> comments, List<Double> ratings) {
		this.comments = comments;
		this.ratings = ratings;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
		
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRating(List<Double> ratings) {
		this.ratings = ratings;
	}	
}
