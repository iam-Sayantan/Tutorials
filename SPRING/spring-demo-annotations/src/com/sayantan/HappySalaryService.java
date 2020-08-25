package com.sayantan;

import org.springframework.stereotype.Component;

@Component
public class HappySalaryService implements SalaryService {

	@Override
	public String getSalary() {
		return "Here is your salary";
	}

}
