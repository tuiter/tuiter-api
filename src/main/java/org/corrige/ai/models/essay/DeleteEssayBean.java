package org.corrige.ai.models.essay;

public class DeleteEssayBean {
	private String userId;
	private String essayId;
	
	public DeleteEssayBean() {}
	
	public DeleteEssayBean(String userId, String essayId) {
		this.userId = userId;
		this.essayId = essayId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEssayId() {
		return essayId;
	}
	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}
}
