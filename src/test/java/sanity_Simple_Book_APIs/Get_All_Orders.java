package sanity_Simple_Book_APIs;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.qa.utils.Authentication;
import com.qa.utils.Orders;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.response.Response;

public class Get_All_Orders extends Baseclass {


	@Test(priority = 1)
	public void get_All_Orders() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to get all the orders")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			String endpoint = "/orders";


			System.out.println("TEST CASE: get_All_Orders Test");
			node.log(Status.INFO, "TEST CASE: get_All_Orders Test");

			given().log().all().header("Authorization", "Bearer " + Authentication.accessAuthToken)

					.when().get(AllPagesUrls.simple_book_API + endpoint).then().statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					// .assertThat().body("status", equalTo("OK"))
					.header("Content-Type", "application/json; charset=utf-8").log().all();

			System.out.println("TEST CASE: get_All_Orders Test pass");
			node.log(Status.INFO, "TEST CASE: get_All_Orders Test pass");

		} catch (AssertionError error) {
			System.out.println("Reason for Assertion error is : " + error);
			node.log(Status.INFO, "Reason for Assertion error is : " + error);
			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();
		}

		catch (Exception error) {

			System.out.println("Reason for Exception error is : " + error);
			node.log(Status.INFO, "Reason for Exception error  is : " + error);
			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();

		}
	}
	
	@Test(priority = 2)
	public void get_Single_Order() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to get the single order")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			String endpoint = "/orders/{orderId}";


			System.out.println("TEST CASE: get_Single_Order ");
			node.log(Status.INFO, "TEST CASE: get_Single_Order ");
			
			given()
	 		.log().all()
			.header("Authorization","Bearer "+Authentication.accessAuthToken)
			.pathParam("orderId", Orders.orderId)
		.when()
			.get(AllPagesUrls.simple_book_API+endpoint)
			
		.then()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				//.assertThat().body("status", equalTo("OK"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();

			System.out.println("TEST CASE: get_Single_Order Test pass");
			node.log(Status.INFO, "TEST CASE: get_Single_Order Test pass");

		} catch (AssertionError error) {
			System.out.println("Reason for Assertion error is : " + error);
			node.log(Status.INFO, "Reason for Assertion error is : " + error);
			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();
		}

		catch (Exception error) {

			System.out.println("Reason for Exception error is : " + error);
			node.log(Status.INFO, "Reason for Exception error  is : " + error);
			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();

		}
	}

}
