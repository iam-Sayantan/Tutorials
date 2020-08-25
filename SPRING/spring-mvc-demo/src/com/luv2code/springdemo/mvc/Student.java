package com.luv2code.springdemo.mvc;

import java.util.LinkedHashMap;

public class Student {
	
	private String firstName;
	private String lastName;
	private String country;
	private LinkedHashMap<String,String> countryOptions;
	private String favoriteLanguage;
	private String[] operatingSystem;
//	private String operatingSystem;
	
	public Student() {
		
		//populate country options: used ISO Country code
		countryOptions = new LinkedHashMap();
		
		countryOptions.put("BR", "Brazil");
		countryOptions.put("FR", "France");
		countryOptions.put("DE", "Germany");
		countryOptions.put("IN", "India");
		countryOptions.put("US", "United States of America");
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// when the form is loaded, Spring will call student.getCountryOptions()
	// this method is called to populate the drop down list or generate the drop down list
	public LinkedHashMap<String, String> getCountryOptions() {
		return countryOptions;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

//	public String getOperatingSystem() {
//		return operatingSystem;
//	}
//
//	public void setOperatingSystem(String operatingSystem) {
//		this.operatingSystem = operatingSystem;
//	}

	public String[] getOperatingSystem() {
		return operatingSystem;
	}

	// On submission, Spring will call student.setOperatingSystem
	public void setOperatingSystem(String[] operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	
	
}
