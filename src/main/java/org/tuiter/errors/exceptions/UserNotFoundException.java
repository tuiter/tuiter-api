package org.tuiter.errors.exceptions;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User already exists");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}

