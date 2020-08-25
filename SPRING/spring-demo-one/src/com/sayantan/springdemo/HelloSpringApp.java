package com.sayantan.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {

		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		//retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach",Coach.class);
		
		//call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		//calling new method for fortunes and salary
		System.out.println(theCoach.getFortuneService());
		System.out.println(theCoach.getSalaryService());

		
		//close the context
		context.close();
	}

}
