package sanity_Simple_Book_APIs;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.qa.utils.Authentication;
import com.qa.utils.Orders;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.http.ContentType;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.HashMap;

public class Post_Submit_An_order extends Baseclass {

	@Test(priority = 1)
	public static void submitAnOrder() {

		try {
			extenttest = extent.createTest("Verify that user is able to submit an order")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");

			String endpoint = "/orders";

			System.out.println("TEST CASE:submitAnOrder");
			node.log(Status.INFO, "TEST CASE: submitAnOrder");
			HashMap<String, Object> dataBody = new HashMap<String, Object>();

			dataBody.put("bookId", "1");

			dataBody.put("customerName", "Vivek");

			String getData = given().contentType(ContentType.JSON).body(dataBody)
					.header("Authorization", "Bearer " + Authentication.accessAuthToken).log().all().when()
					.post(AllPagesUrls.simple_book_API + endpoint).then().assertThat().statusCode(201).assertThat()
					.statusLine("HTTP/1.1 201 Created")
					// .assertThat().body("status", equalTo("OK"))
					.log().all().contentType("application/json; charset=utf-8").extract().response().asString();

			System.out.println("TEST CASE: submitAnOrder pass");
			node.log(Status.INFO, "TEST CASE: submitAnOrder pass");

			System.out.println("Getting Order ID");
			node.log(Status.INFO, "Getting Order ID");

			Object parseObj = null;
			try {
				parseObj = new JSONParser().parse(getData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONObject jsonObj = (JSONObject) parseObj;
			String orderId = (String) jsonObj.get("orderId");
			System.out.println("orderID is " + orderId);

			Orders.orderID(orderId);

			System.out.println("Got Order ID");
			node.log(Status.INFO, "Got Order ID");

		} catch (AssertionError error) {
			System.out.println("Reason for Assertion error is : " + error);
			node.log(Status.INFO, "Reason for Assertion error is : " + error);

			// Call assertionErrorCatchBlock_Data from IScreenAction class
			// IScreenAction.assertionErrorCatchBlock_Data(node, driver, "getStatus",
			// "getStatus_Fail", "StatusTest");

			error.printStackTrace();
			node.fail(error);
			// AssertJUnit.fail();
			extent.flush();
		}

		catch (Exception error) {

			System.out.println("Reason for Exception error is : " + error);
			node.log(Status.INFO, "Reason for Exception error  is : " + error);

			// Call assertionErrorCatchBlock_Data from IScreenAction class
			// IScreenAction.assertionErrorCatchBlock_Data(node, driver, "getStatus",
			// "getStatus_Fail", "StatusTest");

			error.printStackTrace();
			node.fail(error);
			// AssertJUnit.fail();
			extent.flush();

		}
	}

}
