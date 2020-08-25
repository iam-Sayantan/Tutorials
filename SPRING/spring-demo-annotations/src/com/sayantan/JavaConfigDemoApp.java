package com.sayantan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {

		// Load spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		//Get bean from Spring container
		Coach theCoach = context.getBean("tennisCoach",Coach.class);		
		
		//Call a method on bean
		System.out.println(theCoach.getDailyWorkout());
		
		//call method to get Daily Fortune
		System.out.println(theCoach.getDailyFortune());
		
		//call a method to get salary
		System.out.println(theCoach.getSalaryService());
		
		//Close the context
		context.close();
		
	}

}
