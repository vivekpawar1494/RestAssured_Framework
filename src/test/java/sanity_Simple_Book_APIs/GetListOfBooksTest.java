package sanity_Simple_Book_APIs;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.response.Response;

public class GetListOfBooksTest extends Baseclass{
	
	public static String endpoint_list_of_books = "/books";
	
	// Need to send path param in {} and remove the : from the below mentioned URL  documented= /books/:bookId
	public static String endpoint_single_book = "/books/{bookId}";



	@Test(priority = 1)
	public void getListOfBooks() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to get the list of books")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			System.out.println("TEST CASE: getListOfBooks");
			node.log(Status.INFO, "TEST CASE: getListOfBooks");


			Response response = given()

					.when().get(AllPagesUrls.simple_book_API + endpoint_list_of_books)
					

					.then().log().all().statusCode(200).extract().response();

			String JsonString = response.asString();
			AssertJUnit.assertEquals(JsonString.contains("id"), true);
			
			System.out.println("TEST CASE: getListOfBooks pass");
			node.log(Status.INFO, "TEST CASE: getListOfBooks pass");


//			// Take screenshot
//			IScreenAction.captureScreenShot_PassScenario("paymentPageNotAccessibleIfLogout", "Pass", driver);
//			System.out.println("STEP4 : Screenshot Taken");
//			node.log(Status.INFO, "STEP4 : Screenshot Taken");

		} catch (AssertionError error) {
			System.out.println("Reason for Assertion error is : " + error);
			node.log(Status.INFO, "Reason for Assertion error is : " + error);

			// Call assertionErrorCatchBlock_Data from IScreenAction class
			//IScreenAction.assertionErrorCatchBlock_Data(node, driver, "getStatus", "getStatus_Fail", "StatusTest");

			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();
		}

		catch (Exception error) {

			System.out.println("Reason for Exception error is : " + error);
			node.log(Status.INFO, "Reason for Exception error  is : " + error);

			// Call assertionErrorCatchBlock_Data from IScreenAction class
			//IScreenAction.assertionErrorCatchBlock_Data(node, driver, "getStatus", "getStatus_Fail", "StatusTest");

			error.printStackTrace();
			node.fail(error);
			AssertJUnit.fail();
			extent.flush();

		}
	}
	
	@Test(priority = 2)
	public static void singleBook() {
		
		System.out.println("TEST CASE: singleBook ");
		node.log(Status.INFO, "TEST CASE: singleBook ");


		String endpoint = "books/{bookId}";

		given()
			.header("Content-Type", "application/json; charset=utf-8")

			// bookId required to fetch the exact book detail from the
			.pathParam("bookId", 1)
			.log().all()
		.when()
				// Need to send path param in {} and remove the : from the URL
				.get(AllPagesUrls.simple_book_API + endpoint_single_book)
		.then()
			.assertThat().statusCode(200)
			.assertThat().statusLine("HTTP/1.1 200 OK")
			.log().all();
		
		System.out.println("TEST CASE: singleBook pass");
		node.log(Status.INFO, "TEST CASE: singleBook pass");


	}

}
