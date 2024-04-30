package com.qa.utils;


import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.http.ContentType;
import com.qa.utils.RandomInputs;



public class Authentication extends Baseclass{
	

	
public static String accessAuthToken;
	
	public static void accessToken(String accessToken) {
		accessAuthToken = accessToken;
	}
	
	public static String orderId;
	public static void orderID(String order_Id) {
		orderId = order_Id;

	}
	
	public static String ApiUrl = "https://simple-books-api.glitch.me/";


	
	public static void get_auth_token() {
		System.out.println("TEST CASE: get_auth_token()");
		node.log(Status.INFO, "TEST CASE: get_auth_token() ");
		
		 HashMap<String,Object> dataBody = new HashMap<String,Object>();



		String endpoint = "api-clients/";
		String accessToken = null;
		
		dataBody.put("clientName", RandomInputs.name());
		System.out.println(dataBody.put("clientName", RandomInputs.name()));
		dataBody.put("clientEmail", RandomInputs.getEmail());
		System.out.println(dataBody.put("clientEmail", RandomInputs.getEmail()));

		String getData= 
		given()
			.contentType(ContentType.JSON)
			.body(dataBody)
			
		.when()
			.post(ApiUrl+endpoint)
		
		.then()
			.log().all()
			.contentType("application/json;charset=UTF-8").extract().response().asString();
			//.response.getBody().jsonPath().getString("accessToken");

		System.out.println("TEST CASE: get_auth_token pass ");
		node.log(Status.INFO, "TEST CASE: get_auth_token pass");
		
		System.out.println("Getting accessToken");
		node.log(Status.INFO, "Getting accessToken");

	
		Object parseObj = null;
        try {
            parseObj = new JSONParser().parse(getData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = (JSONObject) parseObj;
        accessToken = (String) jsonObj.get("accessToken");
        System.out.println("Access tokken is "+accessToken);
		
        accessToken(accessToken);
        
	}

}