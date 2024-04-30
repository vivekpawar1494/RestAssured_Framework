package com.qa.paths;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;

public class WebdriverPaths extends Baseclass {

	public static void Webdriver_chrome() {
		System.out.println("SETUP-STEP1 : SETUP using Chrome driver");

		// Check the system OS and launch the WebDriver accordingly
		System.out
		.println("SETUP-STEP2 : Checking the system OS to launch the webdriver accordingly");

		if (systemOsName.contentEquals(linuxOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ linuxOS
					+ "---->system, so launching the Chrome webdriver as per that");
			
			System.setProperty("webdriver.chrome.whitelistedIps", "");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");
			
			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		} else if (systemOsName.contentEquals(windowsOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ windowsOS
					+ "---->system, so launching the Chrome webdriver as per that");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");
			
			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		}

		else if (systemOsName.contentEquals(Mac)) {

			System.out
			.println("STEP4 : Code is running in---->"
					+ Mac
					+ "---->system, so launching the Chrome webdriver as per that");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");
			
			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");
		}


	}

	public static void Webdriver_firefox() {
		System.out.println("SETUP-STEP1 : SETUP using Firefox driver");
		node.log(Status.INFO, "SETUP-STEP1 : SETUP using Firefox driver");

		// Check the system OS and launch the webdriver accordingly
		System.out
		.println("SETUP-STEP2 :Checking the system OS to launch the webdriver accordingly");

		if (systemOsName.contentEquals(linuxOS)) {

			System.out
			.println("SETUP-STEP3 :Code is running in---->"
					+ linuxOS
					+ "---->system, so launching the Firefox webdriver as per that");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");
			
			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment_Hulk")
					+ "---->Enviornment");

		} else if (systemOsName.contentEquals(windowsOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ windowsOS
					+ "---->system, so launching the Firefox webdriver as per that");


			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment_Hercules")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment_Navision")
					+ "---->Enviornment");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment_Wulverine")
					+ "---->Enviornment");
			
			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment_Hulk")
					+ "---->Enviornment");

		}


	}

}
