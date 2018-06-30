package org.corrige.ai.validations.exceptions;

public class TopicNotFoundException extends Exception {
private static final long serialVersionUID = 1L;
	
	public TopicNotFoundException(String message) {
		super(message);
	}
	
	public TopicNotFoundException() {
		super("Topic not found.");
	}

}