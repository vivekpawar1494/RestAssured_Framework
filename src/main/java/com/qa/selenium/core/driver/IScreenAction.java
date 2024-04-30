package com.qa.selenium.core.driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;

public class IScreenAction extends Baseclass {

	/**
	 * The "getFileExtenstion" function will convert date format and will return
	 * format in string*
	 *
	 * @author Chirag Mittal
	 * @version 1.0
	 * @since 23.11.2021
	 */
	public static String getFileExtension() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hhmmss");
		String fileExtn = f.format(new Date()) + " "
				+ (calendar.get(Calendar.AM_PM) == 1 ? "PM" : "AM");
		return fileExtn;
	}

	/**
	 * The "createDirectoryIfNotExit" function is used to create folder if not
	 * exit.
	 *
	 * @param folderName
	 *            User can give any folder name
	 * @author Chirag Mittal
	 * @version 1.0
	 * @since 23.11.2021
	 ***/
	public static File createDirectoryIfNotExist(String folderName)
			throws InterruptedException {
		String rootPath = System.getProperty("user.dir");
		File dirPath = new File(rootPath + "/Screenshot/" + folderName + ""
				+ getFileExtension() + "/");
		System.out.println(dirPath);
		if (!dirPath.exists()) {
			if (dirPath.mkdir()) {
				System.out.println("directory created successfully");
			} else {
				System.out.println("directory is not created");
			}
		}
		return dirPath;
	}

	public static File createDirectoryForReport() throws InterruptedException {
		String rootPath = System.getProperty("user.dir");
		File dirPath = new File(rootPath + "/QAAutomationReport/");
		if (!dirPath.exists()) {
			if (dirPath.mkdir()) {
				System.out.println("Folder created for Report");
			} else {
				System.out.println("Folder not created");
			}
		}
		return dirPath;
	}

	public static void captureScreenShot_PassScenario(String folderName,
			String filName, WebDriver driver) {
		try {

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File targetFile = new File(createDirectoryIfNotExist(folderName)
					+ "/" + filName + ".png");
			FileUtils.copyFile(scrFile, targetFile);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return filName;
	}

	public static String captureScreenShot_FailScenario(WebDriver driver,
			String ScreenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy-MM-dd hhmmss")
		.format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")
				+ "/FailedTests_Screenshots/" + ScreenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

	/**
	 * The "assertionErrorCatchBlock_Data" function is used to log the message
	 * and define the file name in case of any assertion failed in test
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance
	 * @param testcaseName
	 *            Pass name of test case against this parameter
	 * @param failedTestCaseFileName
	 *            Pass name by which image file will be saved in case of failed
	 *            test
	 * @param className
	 *            Pass class name against this parameter
	 * @author Chirag Mittal
	 * @version 1.0
	 * @since 25.11.2021
	 */
	public static void assertionErrorCatchBlock_Data(ExtentTest test,
			WebDriver driver, String testcaseName,
			String failedTestCaseFileName, String className) throws IOException {
		System.out
		.println("EXCEPTION BLOCK 1 : FAIL : Some Assertion Exception Caught In "
				+ testcaseName + " test");
		node.log(Status.INFO,
				"EXCEPTION BLOCK 1 : FAIL : Some Assertion Exception Caught In "
						+ testcaseName + " test");

		// Take the screenshot failed test case page
		String screenpath = captureScreenShot_FailScenario(driver,
				failedTestCaseFileName);
		node.log(Status.FAIL, "Some Assertion Error In " + className + " Class"
				+ extenttest.addScreenCaptureFromPath(screenpath));

	}

	/**
	 * The "exceptionErrorCatchBlock_Data" function is used to log the message
	 * and define the file name in case of any exception in test
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance
	 * @param testcaseName
	 *            Pass name of test case against this parameter
	 * @param failedTestCaseFileName
	 *            Pass name by which image file will be saved in case of failed
	 *            test
	 * @param className
	 *            Pass class name against this parameter
	 * @author Chirag Mittal
	 * @version 1.0
	 * @since 25.11.2021
	 */
	public static void exceptionErrorCatchBlock_Data(ExtentTest test,
			WebDriver driver, String testcaseName,
			String failedTestCaseFileName, String className) throws IOException {
		System.out
		.println("EXCEPTION BLOCK 2 :FAIL :  Some exception other then assertion came in "
				+ testcaseName + " test");
		node.log(Status.INFO,
				"EXCEPTION BLOCK 2 : FAIL : Some exception other then assertion came"
						+ testcaseName + " test");

		// Take the screenshot of failed test case page
		String screenpath = captureScreenShot_FailScenario(driver,
				failedTestCaseFileName);
		node.log(Status.FAIL, "Some Exception Error In" + className + " Class"
				+ extenttest.addScreenCaptureFromPath(screenpath));
	}

}
