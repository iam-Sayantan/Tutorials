package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	// Controller method to show HTML Form
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	// Controller method to process the HTML Form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	// new controller method to read form data 
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		//Read the request parameter from the HTML Form
		String theName = request.getParameter("studentName");
		
		//Processing
		//Convert data to uppercase
		theName = theName.toUpperCase();
		
		//create the message
		String result = "Yo !! "+theName;
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
		
	}
	
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName")String theName,
			Model model) {
				
		//Processing
		//Convert data to uppercase
		theName = theName.toUpperCase();
		
		//create the message
		String result = "@RequestParam - "+theName;
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "helloworld";
		
	}
}
