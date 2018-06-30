package org.corrige.ai.models.review;

import java.util.ArrayList;
import java.util.List;

import org.corrige.ai.enums.ReviewStatus;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review {
	@Id
	private String id;
	
	private String userId;
	private String essayId;
	
	private List<String> comments;
	private List<Double> ratings;
	@JsonIgnore
	private ReviewStatus status;
	
	public Review(String userId, String essayId, List<String> comments, List<Double> ratings) {
		this.essayId = essayId;
		this.userId = userId;
		this.comments = comments;
		this.ratings = ratings;
		this.status = ReviewStatus.PENDING;
	}
	
	public Review(String userId, String essayId) {
		this.userId = userId;
		this.essayId = essayId;
		this.comments = new ArrayList();
		this.ratings = new ArrayList();
		this.status = ReviewStatus.PENDING;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", essayId=" + essayId + ", userId=" + userId + ", content="
				+ comments + ", rating=" + ratings + "]";
	}
}
