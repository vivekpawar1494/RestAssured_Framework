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
import com.qa.selenium.core.driver.IScreenAction;
import com.qa.testData.TestData;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.response.Response;

public class GetStatusTest extends Baseclass {
	


	@Test(priority = 1)
	public void getStatus() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to get the STATUS")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			String endPoint = "/status";

			
			System.out.println("TEST CASE: getStatus ");
			node.log(Status.INFO, "TEST CASE: getStatus ");


			Response response = given()

					.when().get(AllPagesUrls.simple_book_API + endPoint)
					

					.then().log().all().statusCode(200).extract().response();

			String JsonString = response.asString();
			Assert.assertEquals(JsonString.contains("OK"), true);
			
			System.out.println("TEST CASE:getStatus pass");
			node.log(Status.INFO, "TEST CASE:getStatus pass");

			

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
}
