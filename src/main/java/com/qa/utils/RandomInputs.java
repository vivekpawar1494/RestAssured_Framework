package com.qa.utils;

import org.apache.commons.lang3.RandomStringUtils;


public class RandomInputs {

	public static String getEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString + "@gmail.com");
		
	}
	
	public static String password() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return (generatedString + "pass");
		
	}
	
	public static String name() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString.toLowerCase());
		
	}
	
	
}
