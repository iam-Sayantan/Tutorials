package com.sayantan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
	
	@Autowired
	@Qualifier("happyFortuneService")
	private FortuneService fortuneService;
	@Autowired
	private SalaryService salaryService;
		
//	@Autowired 
//	public TrackCoach(FortuneService theFortuneService, SalaryService theSalaryService) {
//		fortuneService = theFortuneService;
//		salaryService = theSalaryService;
//	}

	@Override
	public String getDailyWorkout() {
		return "RUN RUN RUN RUN";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String getSalaryService() {
		return salaryService.getSalary();
	}

}
