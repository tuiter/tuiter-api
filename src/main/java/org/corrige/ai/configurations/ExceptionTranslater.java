package org.corrige.ai.configurations;

import org.corrige.ai.validations.ApiError;
import org.corrige.ai.validations.ErrorFactory;
import org.corrige.ai.validations.ValidationError;
import org.corrige.ai.validations.exceptions.EmptyFieldsException;
import org.corrige.ai.validations.exceptions.EssayNotExistsException;
import org.corrige.ai.validations.exceptions.IncorretPasswordException;
import org.corrige.ai.validations.exceptions.InvalidDataException;
import org.corrige.ai.validations.exceptions.NotificationNotExistsException;
import org.corrige.ai.validations.exceptions.RatingNotExistsException;
import org.corrige.ai.validations.exceptions.ReviewNotExistsException;
import org.corrige.ai.validations.exceptions.TopicNotExistsException;
import org.corrige.ai.validations.exceptions.UserAlreadyExistsException;
import org.corrige.ai.validations.exceptions.UserNotExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslater extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError error = ErrorFactory.createValidationError(exception.getBindingResult());
        return super.handleExceptionInternal(exception, error, headers, status, request);
    }
    
    @ExceptionHandler(UserNotExistsException.class)
    public final ResponseEntity<ApiError> handleUserNotExistsException(UserNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(EssayNotExistsException.class)
    public final ResponseEntity<ApiError> handleEssayNotExistsException(EssayNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Essay not found.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(EmptyFieldsException.class)
    public final ResponseEntity<ApiError> handleEmptyFieldsException(EmptyFieldsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Fields cannot be empty.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(ReviewNotExistsException.class)
    public final ResponseEntity<ApiError> handleReviewNotExistsException(ReviewNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Review not found.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "User already exists.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(IncorretPasswordException.class)
    public final ResponseEntity<ApiError> handleIncorretPasswordException(IncorretPasswordException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Incorrect password.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(NotificationNotExistsException.class)
    public final ResponseEntity<ApiError> handleNotificationNotExistsException(NotificationNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Notification not exists!");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(RatingNotExistsException.class)
    public final ResponseEntity<ApiError> handleRatingNotExistsException(RatingNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Rating not found.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<ApiError> handleInvalidDataException(InvalidDataException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Invalid Data");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
    
    @ExceptionHandler(TopicNotExistsException.class)
    public final ResponseEntity<ApiError> handleTopicNotExistsException(TopicNotExistsException exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Topic not exists.");
		return new ResponseEntity<>(apiError, apiError.getCode());
    }
}	
