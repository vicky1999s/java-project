package com.vicky.authentication.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
	private final String message;
	private final HttpStatus httpStatus;
	private String stackTrace;
	private List<ValidationError> errors;

	@Getter
	@Setter
	@RequiredArgsConstructor
	private static class ValidationError {
		private final String field;
		private final String message;
	}

	public void addValidationError(String field, String message) {
		if (Objects.isNull(errors)) {
			errors = new ArrayList<>();
		}
		errors.add(new ValidationError(field, message));
	}
}
