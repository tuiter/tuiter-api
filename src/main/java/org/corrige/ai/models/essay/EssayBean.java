package org.corrige.ai.models.essay;

import org.corrige.ai.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EssayBean {
	private String userUsername;
	private String title;
	private String theme;
	private String content;
	private Type type;
}
