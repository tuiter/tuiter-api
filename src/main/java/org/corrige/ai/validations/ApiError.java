package org.corrige.ai.validations;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ApiError {
	private HttpStatus code;
	private String error;
}
