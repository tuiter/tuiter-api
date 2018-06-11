package org.tuiter.beans;

import org.tuiter.util.NotificationType;

public class NotificationBean {
	private String senderId;

	public NotificationBean() {
		
	}

	public NotificationBean(String senderId, NotificationType type) {
		this.senderId = senderId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
}
