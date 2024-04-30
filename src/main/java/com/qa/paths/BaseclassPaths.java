package com.qa.paths;

import java.io.IOException;
import com.qa.baseclass.Baseclass;



public class BaseclassPaths extends Baseclass {


	public static String environementUrl;
	
	

	public static void property() throws IOException {
		
			
		environementUrl = configFile.getProperty("Environment");
	
		 if (environementUrl.equalsIgnoreCase("stg"))

		{
//			VideoGame_endpoint = "http://localhost:8080/app/videogames";
//			VideoGame_User10_endpoint = "http://localhost:8080/app/videogames/10";
//			VideoGame_User100_endpoint = "http://localhost:8080/app/videogames/101";
//			VideoGame_User1_endpoint = "http://localhost:8080/app/videogames/1";
//
//			environementUrl = "stg1-"; 
			

		}

		else if (environementUrl.equalsIgnoreCase("PROD"))

		{
			environementUrl = "simple-books-api.glitch.me";
			//environementUrl = "prod-";

		}
		
		

	}
}
