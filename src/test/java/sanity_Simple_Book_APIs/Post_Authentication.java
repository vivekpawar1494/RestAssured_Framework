package sanity_Simple_Book_APIs;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;
import com.qa.utils.Authentication;
import com.testdata.allPages.AllPagesUrls;

import io.restassured.response.Response;

public class Post_Authentication extends Baseclass{
	
	@Test(priority = 1)
	public void post_sign_up() throws IOException {
		try {

			extenttest = extent.createTest("Verify that user is able to sign up")
					.assignCategory(SanitySuite + "_" + env_Simple_Book);
			node = extenttest.createNode("Report");
			
			System.out.println("post_sign_up Test");
			node.log(Status.INFO, "post_sign_up Test");

			
			Authentication.get_auth_token();

			System.out.println("post_sign_up Test pass");
			node.log(Status.INFO, "post_sign_up Test pass");

			

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
