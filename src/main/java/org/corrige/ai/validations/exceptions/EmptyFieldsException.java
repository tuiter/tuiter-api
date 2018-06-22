package org.corrige.ai.validations.exceptions;

public class EmptyFieldsException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyFieldsException(String message) {
		super(message);
	}
	
	public EmptyFieldsException() {
		super("Fields can not be empty!");
	}

}
