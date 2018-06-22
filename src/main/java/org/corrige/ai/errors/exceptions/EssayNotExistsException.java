package org.corrige.ai.errors.exceptions;

public class EssayNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public EssayNotExistsException(String message) {
		super(message);
	}
	
	public EssayNotExistsException() {
		super("Essay not exists.");
	}


}
