package com.sayantan.jackson.json.demo;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {

		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert to Java POJO: 
			// data/sample-lite.json
			
			// File with a normal JSON 
//			Student theStudent = mapper.readValue
//					(new File("data/sample-lite.json"), Student.class);
			
			// File with a nested JSON and array
			// create separate class file for the nested json and add it to the main entity class along with Getters and Setters.
			// also add the string field to the entity class along with Getters and Setters.
			Student theStudent = mapper.readValue
					(new File("data/sample-full.json"), Student.class);
			
			// print first name and last name.
			System.out.println("First Name: "+theStudent.getFirstName());
			System.out.println("Last Name: "+theStudent.getLastName());
			
			// print out nested objects i.e. address: street and city
			Address tempAddress = theStudent.getAddress();
						
			System.out.println("Street = " +tempAddress.getStreet());
			System.out.println("Street = "+tempAddress.getCity());
			
			//print out array i.e. languages
			for(String tempLang: theStudent.getLanguages()) {
				System.out.println(tempLang);
			}
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
