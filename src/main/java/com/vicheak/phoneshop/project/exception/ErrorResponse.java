package com.vicheak.phoneshop.project.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private HttpStatus status; 
	private String message; 
}
