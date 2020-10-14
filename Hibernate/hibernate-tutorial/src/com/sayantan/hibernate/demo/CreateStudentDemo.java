package com.sayantan.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sayantan.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory =  new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Create a student Object
			System.out.println("Creating a Student Object");
			
			Student tempStudent = new Student("Sayantan","Sengupta","abc@xyz.com");
			
			// Start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student");
			session.save(tempStudent);
			
			// commit transaction
			System.out.println("Commiting the transaction");
			
			session.getTransaction().commit();
			
			System.out.println("DONE");
			
		} finally {
			factory.close();
		}
		
	}

}
