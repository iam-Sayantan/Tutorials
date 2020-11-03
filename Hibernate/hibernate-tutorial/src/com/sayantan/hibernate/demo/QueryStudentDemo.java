package com.sayantan.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sayantan.hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory =  new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			// Start a transaction
			session.beginTransaction();
						
			// Query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// Display the students
			displayStudents(theStudents);
			
			// Query students: lastName = 'Sengupta'
			theStudents = session.createQuery("from Student s where s.lastName = 'Sengupta'").getResultList();
			
			// Display the students
			System.out.println("\nStudents having 'Sengupta' as last name: ");
			displayStudents(theStudents);
			
			// Query Students: lastName = 'Sengupta' OR firstName = 'Sada'
			theStudents = session.createQuery("from Student s where s.lastName='Sengupta' OR s.firstName='Sada'").getResultList();
			
			// Display the students
			System.out.println("\n\nStudents having 'Sengupta' as last name OR 'Sada' as firstName: ");
			displayStudents(theStudents);
			
			// Query Students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList(); 
			
			// Display the students
			System.out.println("\n\nStudents where email ends with '%luv2code.com'  ");
			displayStudents(theStudents);
			
			
			session.getTransaction().commit();
			
			System.out.println("DONE");
			
		} finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
