package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator 
	implements ConstraintValidator<CourseCode, String>{
	
	private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value(); //.value accesses the attribute value for the given annotation 
	}

	@Override
	public boolean isValid(String theCode, // String theCode --> HTML form data entered by the user; 
				ConstraintValidatorContext theConstraintValidatorContext) {  //ConstraintValidatorContext theConstraintValidatorContext --> additional error messages can be placed here.
		boolean result;
		if (theCode != null) {
		result = theCode.startsWith(coursePrefix);
		} else {
			result = true;
		}
		return result;
	}

}
