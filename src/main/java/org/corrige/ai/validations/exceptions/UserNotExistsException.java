package org.corrige.ai.validations.exceptions;

public class UserNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UserNotExistsException(String message) {
		super(message);
	}
	
	public UserNotExistsException() {
		super("User not exists.");
	}

}
