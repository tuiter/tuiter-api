package org.corrige.ai.models.review;

import org.corrige.ai.models.essay.SimpleEssayBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class EssaysReviews {
	private Review review;
	private SimpleEssayBean essay;
	
	public EssaysReviews(Review review, String title, String theme) {
		this.review = review;
		this.essay = new SimpleEssayBean(title, theme);
	}
}
