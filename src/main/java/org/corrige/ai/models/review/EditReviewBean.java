package org.corrige.ai.models.review;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditReviewBean {
	private List<String> comments;
	private List<Double> ratings;	
}
