package org.tuiter.errors;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ValidationError {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();

	private final String errorMessage;
	
	public ValidationError() {
        this("Validation error!");
    }

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }
}
