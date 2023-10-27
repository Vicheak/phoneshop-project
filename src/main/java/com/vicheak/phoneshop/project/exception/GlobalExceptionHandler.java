package com.vicheak.phoneshop.project.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler { 
	
	@ExceptionHandler(value=ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getStatus())
				.body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<FieldError> fieldErrors = new ArrayList<>();
		e.getFieldErrors().forEach(fieldError -> fieldErrors.add(FieldError.builder()
				.field(fieldError.getField())
				.message(fieldError.getDefaultMessage())
				.build()));
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(fieldErrors); 
	}
	
}
