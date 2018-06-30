package org.corrige.ai.models.essay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteEssayBean {
	private String userId;
	private String essayId;
}
