package com.sayantan.springdemo;

import java.util.Random;

public class RandomFortuneService implements Fortune {

	// create an array of strings
	private String[] data = { 
			"Beware of the wolf in sheep's clothing",
			"Diligence is the mother of good luck",
			"The journey is the reward"
	};
		
	private Random myRandom = new Random();
	
	@Override
	public String getFortuneService() {
		int index = myRandom.nextInt(data.length);
		String newFortune = data[index];
		return newFortune;
	}

}
