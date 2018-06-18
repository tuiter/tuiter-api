package org.tuiter.errors;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus code;
	private String error;
	
	public ApiError(HttpStatus code, String error) {
		this.code = code;
		this.error = error;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
