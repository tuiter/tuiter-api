package org.corrige.ai.validations.exceptions;

public class EssayNotExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EssayNotExistsException(String message) {
		super(message);
	}
	
	public EssayNotExistsException() {
		super("Essay not exists.");
	}


}
