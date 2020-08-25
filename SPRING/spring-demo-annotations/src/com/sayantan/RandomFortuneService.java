package com.sayantan;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	
	//create an array of strings
	private String[] data = {
			"beware of dogs","ki byapar?","hello"
	};

	// create a random number generator
	private Random myRandom = new Random();
	
	
	@Override
	public String getFortune() {
		// pick a random string from array
		int index = myRandom.nextInt(data.length);
		
		String theFortune = data[index];
		
		
		return theFortune;
	}

}
