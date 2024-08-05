package com.google.petshop.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ApiExceptionResponse> idNotFoundExceptionHandler(IdNotFoundException exception)
	{
		ApiExceptionResponse apiExceptionResponse = 
				new ApiExceptionResponse(exception.getClass().toString(),
						exception.getMessage(),HttpStatus.BAD_REQUEST.toString());
		
		
		return new ResponseEntity<ApiExceptionResponse>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiExceptionResponse> validationExceptionHandler(
			                                              ValidationException exception)
	{
		ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
		apiExceptionResponse.setExceptioName(exception.getClass().toString());
		apiExceptionResponse.setMessage(exception.getMessage());
		apiExceptionResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
		
		return new ResponseEntity<ApiExceptionResponse>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

}
