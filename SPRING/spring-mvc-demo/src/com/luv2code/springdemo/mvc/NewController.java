package com.luv2code.springdemo.mvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
	
	@RequestMapping("/showForm")
	 public String displayTheForm() {
		return "silly";
	}
}
