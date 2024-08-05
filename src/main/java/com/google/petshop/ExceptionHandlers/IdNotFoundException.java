package com.google.petshop.ExceptionHandlers;

public class IdNotFoundException extends RuntimeException {
	
	public IdNotFoundException()
	{
		super();
	}
	public IdNotFoundException(String message)
	{
		super(message);
	}

}
