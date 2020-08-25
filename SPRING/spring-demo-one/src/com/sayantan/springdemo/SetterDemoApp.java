package com.sayantan.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

	public static void main(String[] args) {
		
		//load the spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve the bean
		CricketCoach theCoach = context.getBean("myCricketCoach",CricketCoach.class);
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getFortuneService());
		System.out.println(theCoach.getSalaryService());
		
		//calling new methods to get literal value
		System.out.println(theCoach.getEmailAddress());
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();
	}

}
