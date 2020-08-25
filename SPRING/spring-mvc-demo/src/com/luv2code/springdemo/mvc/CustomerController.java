package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	
	// add an initBinder to convert trim input strings
	// remove leading and trailing whitespaces
	// remove issue for validation(including white spaces for validation - issue)
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	
	@RequestMapping("/showForm")
	public String showModel(Model theModel) {
		
		theModel.addAttribute("customer",new Customer());
		
		return "CustomerForm";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
							BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "CustomerForm";
		} 
		else {
			return "CustomerConfirmation";
		}
	}
}
