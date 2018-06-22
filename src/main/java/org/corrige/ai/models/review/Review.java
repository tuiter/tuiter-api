package org.corrige.ai.models.review;

import java.util.List;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.models.notification.Notification;
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
	
	private String essayId;
	private String userId;
	
	private List<String> comments;
	private List<Double> ratings;
	@JsonIgnore
	private ReviewStatus status;
	
	public Review(String essayId, String userId, List<String> comments, List<Double> ratings) {
		this.essayId = essayId;
		this.userId = userId;
		this.comments = comments;
		this.ratings = ratings;
		this.status = ReviewStatus.PENDING;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", essayId=" + essayId + ", userId=" + userId + ", content="
				+ comments + ", rating=" + ratings + "]";
	}
}
