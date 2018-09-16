package org.corrige.ai.models.essay;

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
public class Essay {
	@Id
	private String id;

	private String userId;
	private String title;
	private String theme;
	private String content;
	private Type type;
	private String topicId;
	private Boolean premium;
	
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
	}
	
	public Essay(String userId, String title, String content, Type type, String topicId, Boolean premium) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.status = ReviewStatus.PENDING;
		this.topicId = topicId;
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Essay [id=" + id + ", userId=" + userId + ", title=" + title + ", theme=" + theme + ", content="
				+ content + ", type=" + type + "]";
	}
}