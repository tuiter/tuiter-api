package org.corrige.ai.validations.exceptions;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		super("Invalid data.");
	}
	
	public InvalidDataException(String message) {
		super(message);
	}
}
