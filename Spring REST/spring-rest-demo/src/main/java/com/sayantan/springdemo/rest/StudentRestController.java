package com.sayantan.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayantan.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;

	// Define @PostConstruct to load student data only once. Only works after bean
	// construction
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();

		theStudents.add(new Student("Sayantan", "Sengupta"));
		theStudents.add(new Student("Sajeed", "Sarkar"));
		theStudents.add(new Student("Kaustav", "Chatterjee"));
		theStudents.add(new Student("Sourav", "Sinha"));
	}

	// define end point for "/students" - return list of students

	@GetMapping("/students")
	public List<Student> getStudents() {

		return theStudents;
	}

	// define endpoint for "/students/{studentId}" - return student at index

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) { // By default, variables should match;

		// Just index into the list
		// check the studentId against the list size
		if ((studentId >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student Id not found - " + studentId);
		}
		return theStudents.get(studentId); // theStudents is the name of the list
	}

}
