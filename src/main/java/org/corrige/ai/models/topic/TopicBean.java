package org.corrige.ai.models.topic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicBean {
	private String theme;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date beginDate;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date endDate;

}
