package com.vicheak.phoneshop.project.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {
	private String field; 
	private String message; 
}
