package org.corrige.ai.errors.exceptions;

public class NotificationNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public NotificationNotExistsException(String message) {
		super(message);
	}
	
	public NotificationNotExistsException() {
		super("Notification not exists.");
	}


}
