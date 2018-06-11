package org.tuiter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.tuiter.util.ReviewStatus;
import org.tuiter.util.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviews")
public class Essay {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String theme;
	
	@Column(nullable = false)
	private String content;
	private Type type;
	
	@JsonIgnore
	private ReviewStatus status;
	
	public Essay(String userId, String title, String theme, String content, Type type) {
		super();
		this.userId = userId;
		this.title = title;
		this.theme = theme;
		this.content = content;
		this.type = type;
		this.status = ReviewStatus.PENDING;
	}
	
	public Essay() {
		
	}
	
	public Essay(String userId, String title, String content, Type type) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.status = ReviewStatus.PENDING;
	}

	public String getId() {
		return id;
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public ReviewStatus getStatus() {
		return status;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Essay other = (Essay) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Essay [id=" + id + ", userId=" + userId + ", title=" + title + ", theme=" + theme + ", content="
				+ content + ", type=" + type + "]";
	}
}