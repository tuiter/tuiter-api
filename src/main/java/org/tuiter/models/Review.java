package org.tuiter.models;

import org.springframework.data.annotation.Id;

public class Review {
	@Id
	private String id;
	
	private String essayId;
	private String userId;
	
	private String title;
	private String content;
	private int rating;
	
	public Review(String essayId, String userId, String title, String content, int rating) {
		this.essayId = essayId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.rating = rating;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
				+ content + ", rating=" + rating + "]";
	}
}
