package com.sayantan;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach {

	
	private FortuneService fortuneService;
	private SalaryService salaryService;
	
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	
	public SwimCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	public SwimCoach(SalaryService theSalaryService) {
		salaryService = theSalaryService;
	}
	
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "swim 500m as a warm up.";
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

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}

	
	
}
