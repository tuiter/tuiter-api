package org.corrige.ai.errors.exceptions;

public class IncorretPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorretPasswordException() {
		super("User already exists");
	}
	
	public IncorretPasswordException(String message) {
		super(message);
	}
}
