package org.tuiter.beans;

import java.util.List;

public class EditReviewBean {
	
	private String title;
	private List<String> comments;
	private List<Double> ratings;

	public EditReviewBean() {
		
	}

	public EditReviewBean(String title, List<String> comments, List<Double> ratings) {
		this.title = title;
		this.comments = comments;
		this.ratings = ratings;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
