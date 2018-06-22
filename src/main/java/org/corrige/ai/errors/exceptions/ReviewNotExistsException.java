package org.corrige.ai.errors.exceptions;

public class ReviewNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public ReviewNotExistsException(String message) {
		super(message);
	}
	
	public ReviewNotExistsException() {
		super("Review not exists.");
	}


}
