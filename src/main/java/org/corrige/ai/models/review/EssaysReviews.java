package org.corrige.ai.models.review;

import org.corrige.ai.models.essay.SimpleEssayBean;

public class EssaysReviews {
	private Review review;
	private SimpleEssayBean essay;
	
	public EssaysReviews() {}
	
	public EssaysReviews(Review review, String title, String theme) {
		super();
		this.review = review;
		this.essay = new SimpleEssayBean(title, theme);
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public SimpleEssayBean getEssay() {
		return essay;
	}

	public void setEssay(SimpleEssayBean essay) {
		this.essay = essay;
	}
}
