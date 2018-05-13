package org.tuiter.errors.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.tuiter.errors.ErrorCode;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TuiterApiException extends RuntimeException {
	private static final long serialVersionUID = 5177358509256964374L;
	
	private HttpStatus status;
	private ErrorCode code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	public TuiterApiException() {
		this("Server error.");
	}
	
	public TuiterApiException(String message) {
		this(message, HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DEFAULT);
	}
	
	
	public TuiterApiException(String message, HttpStatus status, ErrorCode code) {
		super(message);
		
		this.status = status;
		this.code = code;
		this.timestamp = LocalDateTime.now();
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public ErrorCode getCode() {
		return code;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
