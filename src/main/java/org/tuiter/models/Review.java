package org.tuiter.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.tuiter.util.ReviewStatus;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false)
	private String essayId;
	
	@Column(nullable = false)
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

	public String getId() {
		return id;
	}

	public String getEssayId() {
		return essayId;
	}

	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}

	public ReviewStatus getStatus() {
		return this.status;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", essayId=" + essayId + ", userId=" + userId + ", content="
				+ comments + ", rating=" + ratings + "]";
	}
}
