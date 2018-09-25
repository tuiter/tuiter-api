package org.corrige.ai.models.essay;

import java.util.Date;

import org.corrige.ai.enums.ReviewStatus;
import org.corrige.ai.enums.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "essays")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Essay implements Comparable<Essay>{
	@Id
	private String id;

	private String userId;
	private String title;
	private String theme;
	private String content;
	private Type type;
	private String topicId;
	private Boolean premium;
	private Date createdAt;
	
	@JsonIgnore
	private ReviewStatus status;
	
	public Essay(String userId, String title, String theme, String content, Type type, String topicId, Boolean premium) {
		this.userId = userId;
		this.title = title;
		this.theme = theme;
		this.content = content;
		this.type = type;
		this.status = ReviewStatus.PENDING;
		this.topicId = topicId;
		this.premium = premium;
		this.createdAt = new Date();
	}
	
	public Essay(String userId, String title, String content, Type type, String topicId, Boolean premium) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.status = ReviewStatus.PENDING;
		this.topicId = topicId;
		this.premium = premium;
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Essay [id=" + id + ", userId=" + userId + ", title=" + title + ", theme=" + theme + ", content="
				+ content + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Essay other) {
		if (this.getPremium() && other.getPremium())
			return 0;
		else if(this.getPremium() && !other.getPremium())
			return 1;
		else
			return -1;
	}
}