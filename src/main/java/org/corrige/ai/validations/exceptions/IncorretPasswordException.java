package org.corrige.ai.validations.exceptions;

public class IncorretPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorretPasswordException() {
		super("User already exists");
	}
	
	public IncorretPasswordException(String message) {
		super(message);
	}
}
