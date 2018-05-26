package org.tuiter.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Review {
	@Id
	private String id;
	
	private String essayId;
	private String userId;
	
	private String title;
	private List<String> comments;
	private List<Double> ratings;
	
	public Review(String essayId, String userId, String title, List<String> comments, List<Double> ratings) {
		this.essayId = essayId;
		this.userId = userId;
		this.title = title;
		this.comments = comments;
		this.ratings = ratings;
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

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
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
		return "Review [id=" + id + ", essayId=" + essayId + ", userId=" + userId + ", title=" + title + ", content="
				+ comments + ", rating=" + ratings + "]";
	}
}
