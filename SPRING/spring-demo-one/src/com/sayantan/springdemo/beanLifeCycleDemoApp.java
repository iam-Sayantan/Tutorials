package com.sayantan.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanLifeCycleDemoApp {

	public static void main(String[] args) {

		//load the config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		
		//retrieve the bean
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		
		
		//close context
		context.close();
		
	}

}
