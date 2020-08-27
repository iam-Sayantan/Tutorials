package com.sayantan.springdemo.rest;

//Extending from Runtime Exception Superclass
public class StudentNotFoundException extends RuntimeException{

	//Generate Constructors from SuperClass "RuntimeException"
	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}	
}
