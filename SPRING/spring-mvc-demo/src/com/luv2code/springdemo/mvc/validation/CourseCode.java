package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = CourseCodeConstraintValidator.class) // CourseCodeConstraintValidator.class --> Helper Class that contains business rules/ validation logic
@Target({ElementType.METHOD, ElementType.FIELD}) // Can apply annotation to a method ot field 
@Retention(RetentionPolicy.RUNTIME) // Retain this annotation in the Java class file i.e. the bytecode . Process it at runtime by the JVL
public @interface CourseCode {
	
	// define default course code
	public String value() default "LUV";
	
	// define default error message
	public String message() default "must start with LUV";
	
	// define default groups - group is where we can actually group validation constraints together.
	public Class<?>[] groups() default {}; 
	
	// define default payload - payload is where we can give additional information such as security level, error code, etc. about the validation error. 
	public Class<? extends Payload>[] payload() default {};
}
