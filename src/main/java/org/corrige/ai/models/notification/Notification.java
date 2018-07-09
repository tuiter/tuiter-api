package org.corrige.ai.models.notification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "notifications")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification {
	@Id
	private String id;
	
	private String userId;
	private String timeStamp;
	private String description;
	private boolean isNew;
	
	public Notification(String userId, String timeStamp, String description, boolean isNew) {
		this.userId = userId;
		this.timeStamp = timeStamp;
		this.description = description;
		this.isNew = isNew;
	}
}
