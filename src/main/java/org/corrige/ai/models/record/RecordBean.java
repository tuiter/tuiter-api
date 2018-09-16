package org.corrige.ai.models.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecordBean {
	private String essayId;
	private Float value;
}
