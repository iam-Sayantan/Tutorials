package com.sayantan.springdemo;

public class FootballCoach implements Coach{
	
	//create pvt field for dependency
	private Fortune fortuneService;
	private Salary sal;
	
	
	//create constructor
	public FootballCoach(Fortune theFortuneService, Salary theSalaryService) {
		fortuneService = theFortuneService;
		sal = theSalaryService;
	}
		
	public FootballCoach() {
		
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 mins on passing";
	}

	@Override
	public String getFortuneService() {
		return fortuneService.getFortuneService();
	}

	@Override
	public String getSalaryService() {
		return sal.getSalaryService();
	}

	

	
	
}
