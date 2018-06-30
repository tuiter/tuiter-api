package org.corrige.ai.models.review;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewBean {
	private String essayId;
	private String reviewingUserId;
	
	private List<String> comments;
	private List<Double> ratings;
}
