package org.corrige.ai.validations.exceptions;

public class PackNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PackNotFoundException() {
		super("Pack not found.");
	}
}
