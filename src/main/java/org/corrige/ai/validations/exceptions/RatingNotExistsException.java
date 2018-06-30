package org.corrige.ai.validations.exceptions;

public class RatingNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public RatingNotExistsException(String message) {
		super(message);
	}
	
	public RatingNotExistsException() {
		super("Rating not exists.");
	}


}
