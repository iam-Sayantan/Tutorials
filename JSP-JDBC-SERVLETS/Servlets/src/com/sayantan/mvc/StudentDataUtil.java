package com.sayantan.mvc;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	public static List<Student> getStudents(){
		
		//create an empty list
		List<Student> students = new ArrayList<>();		
		
		//add sample data
		students.add(new Student("Sayantan","Sengupta","sayantansengupta98@gmail.com"));
		students.add(new Student("Sourav","Sengupta","souravsinha@gmail.com"));
		students.add(new Student("Sajeed","Sengupta","sajeedsarkar@gmail.com"));
		students.add(new Student("Kaustav","Sengupta","kaustavchatterjee@gmail.com"));
		
		//return list
		return students;
		
	}
}
