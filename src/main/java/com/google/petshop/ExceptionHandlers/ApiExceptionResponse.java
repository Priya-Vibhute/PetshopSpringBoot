package com.google.petshop.ExceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiExceptionResponse {
	private String exceptioName;
	private String message;
	private String status;

}
