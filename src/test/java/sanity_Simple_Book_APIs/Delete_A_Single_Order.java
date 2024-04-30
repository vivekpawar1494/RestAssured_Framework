package sanity_Simple_Book_APIs;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.qa.utils.Authentication;
import com.qa.utils.Orders;
import com.testdata.allPages.AllPagesUrls;

public class Delete_A_Single_Order extends Baseclass{

	@Test(priority = 1)
	public void delete_Single_Order() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to delete the order")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			System.out.println("TEST CASE: delete_Single_Order ");
			node.log(Status.INFO, "TEST CASE: delete_Single_Order");


			HashMap<String, Object> dataBody = new HashMap<String, Object>();

			String endpoint = "/orders/{orderId}";
			dataBody.put("customerName", "John");
			
			given()
		 		.log().all()
				.header("Authorization","Bearer "+Authentication.accessAuthToken)
				.pathParam("orderId", Orders.orderId)
				.body(dataBody)
			.when()
				.delete(AllPagesUrls.simple_book_API+endpoint)
				
			.then()
					.statusCode(204)
					.statusLine("HTTP/1.1 204 No Content")
					//.assertThat().body("status", equalTo("OK"))
					//.header("Content-Type", "text/plain; charset=ISO-8859-1")
					.log().all();
			System.out.println("TEST CASE: delete_Single_Order pass");
			node.log(Status.INFO, "TEST CASE: delete_Single_Order pass");

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
