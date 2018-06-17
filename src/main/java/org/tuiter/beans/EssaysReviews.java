package org.tuiter.beans;

import org.tuiter.models.Review;

public class EssaysReviews {
	Review review;
	Essay essay;
	
	public EssaysReviews() {}
	
	public EssaysReviews(Review review, String title, String theme) {
		super();
		this.review = review;
		this.essay = new Essay(title, theme);
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Essay getEssay() {
		return essay;
	}

	public void setEssay(Essay essay) {
		this.essay = essay;
	}
}
