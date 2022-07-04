package com.vicky.authentication.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {EmailAlreadyExistsException.class})
	public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		ErrorResponse error = new ErrorResponse("Validation error. Check 'errors' field for details.", HttpStatus.UNPROCESSABLE_ENTITY);
		for(FieldError e : ex.getBindingResult().getFieldErrors()) {
			error.addValidationError(e.getField(), e.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(error);	
	}
	
	
	@ExceptionHandler(value = {BusinessException.class})
	public ResponseEntity<?> handleBusinessException(BusinessException ex){
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> handleAllUncaughtException(Exception ex){
		ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
	}
	
}
