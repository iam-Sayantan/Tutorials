package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
		
	    @RequestMapping("/showForm") // mapping
	    public String showForm(Model theModel) { //instantiating an object of type Model and 
	    										 //adding reference to that model under name theModel

	    	
	    //Create a student object
	    Student theStudent = new Student(); //creating fresh empty object of type Student 
	    									//and adding its reference name theStudent
	    
	    
	    //Add student object to the model
	     theModel.addAttribute("student",theStudent);//using Model object that we 
	     											 //instantiate with name theModel, we are calling method of an object Model
	     											 //	addAttribute, we are passing first paramter as a string "student"
	     											 //	( that keyword is link between contoller and jsp ) that we will use
	     											 //in our jsp page,
	     											 //and second paramter is an empty theStudent object, 
	     											 //that we will fill in with data in student-form
		
		
		return "student-form";
		
		
		
	}
	
@RequestMapping("/processForm")
public String processForm(@ModelAttribute("student") Student theStudent) {
	
	//log the input data
	System.out.println("theStudent: " +theStudent.getFirstName()+" "+theStudent.getLastName()+" "+theStudent.getCountry());
	
	return "student-confirmation";
}
	
}
