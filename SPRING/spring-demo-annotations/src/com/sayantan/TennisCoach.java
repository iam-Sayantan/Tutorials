package com.sayantan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
	
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	@Autowired
	private SalaryService salaryService;
	
	/*
	@Autowired 
	public TennisCoach(FortuneService theFortuneService, SalaryService theSalaryService) {
		fortuneService = theFortuneService;
		salaryService = theSalaryService;
	}
	*/
	
	public TennisCoach() {
		System.out.println(">> TennisCoach : Inside default constructor - optional step");
	}
	
	// Define the INIT Method
	
	@PostConstruct
	public void callInitMethod() {
		System.out.println(">>TennisCoach: Inside of callInitMethod() ");
	}
	
	// Define the DESTROY method
	@PreDestroy
	public void callDestroyMethod() {
		System.out.println(">>TennisCoach: Inside of callDestroyMethod() ");
	}
	
	//Define a setter method
//	@Autowired
//	public void setFortuneService(FortuneService theFortuneService) {
//		System.out.println(">>Autowired<< TennisCoach : Inside Fortune Service - optional step");
//		fortuneService = theFortuneService;
//	}
//	
	
	
	/*
	@Autowired
	public void GiveMeSalary(SalaryService theSalaryService) {
		System.out.println(">>Autowired<< TennisCoach : Inside Salary Service - optional step");
		salaryService = theSalaryService;
	}
	*/

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}
	
	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

	@Override
	public String getSalaryService() {
		return salaryService.getSalary();
	}

}
