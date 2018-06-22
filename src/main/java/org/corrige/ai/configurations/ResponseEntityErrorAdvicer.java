package org.corrige.ai.configurations;

import org.corrige.ai.validations.ErrorFactory;
import org.corrige.ai.validations.ValidationError;
import org.corrige.ai.validations.exceptions.TuiterApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityErrorAdvicer extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError error = ErrorFactory.createValidationError(exception.getBindingResult());
        return super.handleExceptionInternal(exception, error, headers, status, request);
    }
    
    @ExceptionHandler
    protected ResponseEntity<TuiterApiException> handleApiException(TuiterApiException exception) {
    	return new ResponseEntity<>(exception, exception.getStatus());
    }
}	
