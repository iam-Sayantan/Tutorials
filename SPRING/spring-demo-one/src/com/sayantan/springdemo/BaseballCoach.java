package com.sayantan.springdemo;

public class BaseballCoach implements Coach {
	
	//create pvt field for dependency
		private Fortune fortuneService;
		private Salary sal;
		
		//create constructor
		public BaseballCoach(Fortune theFortuneService, Salary theSalaryService) {
			fortuneService = theFortuneService;
			sal = theSalaryService;
		}

		public BaseballCoach() {
			
		}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 mins on shot practice";
	}

	@Override
	public String getFortuneService() {
		return fortuneService.getFortuneService();
	}

	@Override
	public String getSalaryService() {
		return sal.getSalaryService();
	}
	
	// add an init method
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method doMyStartupStuff");
	}
	
	// add a destroy method
	public void doMyCleanupStuff() {
		System.out.println("TrackCoach: inside method doMyCleanupStuff");
	}
	
}
