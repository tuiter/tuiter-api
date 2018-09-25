package org.corrige.ai.models.review;

import java.util.List;
import java.util.Optional;

import org.corrige.ai.enums.RatingClass;
import org.corrige.ai.models.rating.Rating;
import org.corrige.ai.util.Vote;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MinimalReview {
	private Vote vote;
	private RatingClass rating;

	public MinimalReview(Optional<Rating> optional, List<Double> ratings) {
		if(optional.isPresent())
			optional.get().getVote();
		this.rating = calcRatingClass(ratings);
	}
	
	private RatingClass calcRatingClass(List<Double> ratings) {
		Optional<Double> ratingOP = ratings.stream().reduce((x, y) -> x = y);
		if(!ratingOP.isPresent())
			return null;
		
		Double rating = ratingOP.get();
		if(rating < 300)
			return RatingClass.ONE;
		else if(rating < 700)
			return RatingClass.TWO;
		else if(rating < 900)
			return RatingClass.THREE;
		return RatingClass.FOUR;
	}
	
	
}
