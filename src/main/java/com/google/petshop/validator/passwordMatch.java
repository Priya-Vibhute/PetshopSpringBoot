package com.google.petshop.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy =PasswordMatchValidator.class )
public @interface passwordMatch {
	String message() default "Password and confirm password does not match";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
