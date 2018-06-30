package org.corrige.ai.validations.exceptions;

public class TopicNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TopicNotExistsException(String message) {
		super(message);
	}
	
	public TopicNotExistsException() {
		super("Topic not exists.");
	}

}