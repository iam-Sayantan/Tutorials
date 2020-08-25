package com.sayantan.springdemo;

public class CricketCoach implements Coach {
	
	//create private fields
	private Fortune fortuneService;
	private Salary sal;
	private String emailAddress;
	private String team;
	
	//create a constructor
	
//	public CricketCoach() {
//		System.out.println("CricketCoach: inside no-arg constructor");
//	}
//	
	// Create setter methods

	//calling the getter methods to print some lines to test our app
	public String getEmailAddress() {
		return emailAddress;
	}



	public String getTeam() {
		return team;
	}



	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach: inside setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}



	public void setTeam(String team) {
		System.out.println("CricketCoach: inside setter method - setTeam");
		this.team = team;
	}



	public void setFortuneService(Fortune fortuneService) {
		System.out.println("CricketCoach: inside setter method - setFortuneService");
		this.fortuneService = fortuneService;
	}



	public void setSal(Salary sal) {
		System.out.println("CricketCoach: inside setter method - setSal");
		this.sal = sal;
	}



	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Practice Sprinting";
	}

	@Override
	public String getFortuneService() {
		// TODO Auto-generated method stub
		return fortuneService.getFortuneService();
	}

	@Override
	public String getSalaryService() {
		// TODO Auto-generated method stub
		return sal.getSalaryService();
	}

}
