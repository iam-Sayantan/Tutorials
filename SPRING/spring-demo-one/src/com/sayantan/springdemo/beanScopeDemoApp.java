package com.sayantan.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanScopeDemoApp {

	public static void main(String[] args) {

		//load the config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		//retrieve the bean
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		//check if they are same 
		boolean result = (theCoach == alphaCoach);
		
		//print result
		System.out.println("Pointing to the same object: "+result);
		System.out.println("\n Memory location for theCoach: "+theCoach);
		System.out.println("\n Memory location for alphaCoach: "+alphaCoach);

		//close context
		context.close();
		
	}

}
