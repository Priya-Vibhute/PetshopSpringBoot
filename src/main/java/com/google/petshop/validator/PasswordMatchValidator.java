package com.google.petshop.validator;

import com.google.petshop.dto.UserDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<passwordMatch, UserDto>{

	@Override
	public boolean isValid(UserDto value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value.getPassword().equals(value.getConfirmPassword());
	}

}
