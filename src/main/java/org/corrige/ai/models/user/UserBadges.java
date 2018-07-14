package org.corrige.ai.models.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserBadges {
	private String userId;
	private Integer createdEssays;
	private Integer reviewedEssays;
	
	public UserBadges(String userId, Integer createdEssays, Integer reviewedEssays) {
		this.userId = userId;
		this.createdEssays = createdEssays;
		this.reviewedEssays = reviewedEssays;
	}

}
