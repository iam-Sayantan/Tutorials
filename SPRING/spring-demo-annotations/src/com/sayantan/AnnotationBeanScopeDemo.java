package com.sayantan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemo {

	public static void main(String[] args) {

		// load spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve the bean from spring container
		Coach theCoach = context.getBean("tennisCoach",Coach.class);
		Coach alphaCoach = context.getBean("tennisCoach",Coach.class);
		
		// check if they are the same
		
		boolean result = (theCoach == alphaCoach);
		
		// print out results
		
		System.out.println("\n Pointing towards same object: "+result);
		System.out.println("\n Memory location for theCoach: "+theCoach);
		System.out.println("\n Memory location for alphaCoach: "+alphaCoach);
		
	}

}
