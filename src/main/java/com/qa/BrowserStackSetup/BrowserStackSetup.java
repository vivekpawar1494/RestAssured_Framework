package com.qa.BrowserStackSetup;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;
import com.qa.baseclass.Baseclass;

public class BrowserStackSetup extends Baseclass {

	// Browsertack account configuration
	public static final String AUTOMATE_USERNAME = configFile
			.getProperty("BrowserstackUserName");
	public static final String AUTOMATE_ACCESS_KEY = configFile
			.getProperty("BrowserstackPassword");
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":"
			+ AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

	// Browserstack setup on Chrome Browser For Windows 10
	public static WebDriver setupChromeBrowserstack() throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		caps.setCapability("browserstack.timezone", "Kolkata");
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "latest");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1440x900");
		
		// set the timeout to a maximum of 300 seconds
		caps.setCapability("browserstack.idleTimeout", "500");
		// options.addArguments("start-maximized");
		caps.setCapability("browserstack.local", true);
		// caps.setCapability("browserstack.localIdentifier", "Test123");
		caps.setCapability("unhandledPromptBehavior", "ignore");

		// Creates an instance of Local
		Local bsLocal = new Local();

		// You can also set an environment variable - "BROWSERSTACK_ACCESS_KEY".
		HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
		bsLocalArgs.put("key", configFile.getProperty("BrowserstackPassword"));

		// Starts the Local instance with the required arguments
		bsLocal.start(bsLocalArgs);

		// Check if BrowserStack local instance is running
		System.out.println(bsLocal.isRunning());

		driver = new RemoteWebDriver(new URL(URL), caps);
		return driver;
	

	}

}
