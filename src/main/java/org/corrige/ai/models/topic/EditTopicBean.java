package org.corrige.ai.models.topic;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditTopicBean {
	private String theme;
	private Date beginDate;
	private Date endDate;

}
