package org.corrige.ai.models.topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.corrige.ai.models.essay.Essay;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "topics")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic {
	@Id
	private String id;
	
	private String theme;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date beginDate;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date endDate;

	private List<Essay> essays;
	
	public Topic(String theme, Date beginDate, Date endDate) {
		this.theme = theme;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.essays = new ArrayList<Essay>();
	}
}

