package org.corrige.ai.errors;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorFactory {
	public static ValidationError createValidationError(Errors errors) {
		ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
		
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        
        return error;
	}
}
