package com.sayantan.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sayantan.hibernate.entity.Student;

public class ReadStudentDemo {

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
			
			Student tempStudent = new Student("Sada","SG","sada@xyz.com");
			
			// Start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit transaction
			System.out.println("Commiting the transaction");
			
			session.getTransaction().commit();
			
			// Find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// Get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Read/Retrieve student based on the id: primary key
			System.out.println("\n Getting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// Commit the transaction 
			session.getTransaction().commit();
			
			System.out.println("DONE");
			
		} finally {
			factory.close();
		}
		
	}

}
