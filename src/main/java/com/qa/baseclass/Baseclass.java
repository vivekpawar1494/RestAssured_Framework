package com.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.qa.BrowserStackSetup.BrowserStackSetup;
import com.qa.paths.BaseclassPaths;
import com.qa.paths.WebdriverPaths;
import com.qa.selenium.core.driver.IScreenAction;
import com.qa.testlistener.TestListeners;
import com.qa.utils.Authentication;

@Listeners(TestListeners.class)
public class Baseclass {

	public static String env_Simple_Book;
	public static String user_Name;
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest extenttest;
	public static ExtentTest node;
	public static WebDriver driver;
	public static Properties configFile = new Properties();
	public static String systemOsName = System.getProperty("os.name");
	public static String windowsOS = "Windows 10";
	public static String linuxOS = "Linux";
	public static String Mac = "Mac OS X";
	public static String SanitySuite = "SanitySuite_Simple_Book_APIs";
	public static File src;

	 
	//@Parameters({ "Browser" })
	@BeforeSuite
	public static void setUp() throws Exception {

		// Config Property File
		File ConfigFile = new File(System.getProperty("user.dir") + "/ConfigFile/Config.properties");

		FileInputStream fileInput3 = null;
		fileInput3 = new FileInputStream(ConfigFile);
		configFile.load(fileInput3);

		// Initializing the property method by calling property function from
		// BaseclassPaths
		BaseclassPaths.property();

		// Create object of baseclasspath and access its variable
		BaseclassPaths obj = new BaseclassPaths();
		env_Simple_Book = configFile.getProperty("Environment");
		user_Name = configFile.getProperty("User_Name");

		// Create report folder in project root
		IScreenAction.createDirectoryForReport();

		// Extent Report Setup
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/QAAutomationReport/Report_"
				+ IScreenAction.getFileExtension() + "Spark.html").viewConfigurer().viewOrder()
						.as(new ViewName[] { ViewName.CATEGORY, ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION,
								ViewName.AUTHOR, ViewName.DEVICE, ViewName.LOG })
						.apply();
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	//	extent.setSystemInfo("OS", systemOsName);
		//extent.setSystemInfo("Host Name", user_Name);
		//extent.setSystemInfo("Environment", env_Simple_Book);
		//extent.setSystemInfo("Url_VideoGame", url_VideoGame);
		reporter.config().setDocumentTitle("Automation-Testing");
		reporter.config().setReportName("Project-AutomationReport");
		reporter.config().setTheme(Theme.DARK);

	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			node.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " :Test case FAILED due to below issues:",
					ExtentColor.RED));
			node.log(Status.FAIL, result.getThrowable());

			// test1.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			node.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " :Test Case PASSED", ExtentColor.GREEN));

		} else if (result.getStatus() == ITestResult.SKIP) {
			node.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " :Test Case SKIPPED ", ExtentColor.YELLOW));

		}
	}

	public static String getExcelSheetData(int col2RowValue) throws IOException, FileNotFoundException {
		if (BaseclassPaths.environementUrl.equalsIgnoreCase("PROD")) {

			System.out.println("User is running the code on Production so fetching the TestData as per that");

			// Import excel sheet.
			src = new File("./ProductionTestData.xlsx");

		}

		else if (BaseclassPaths.environementUrl.equalsIgnoreCase("stg")) {

			System.out.println("User is running the code on Staging so fetching the TestData as per that");

			// Import excel sheet.
			src = new File("./StagingTestData.xlsx");

		}
		

		ArrayList<Object> arrObj = new ArrayList<Object>();
		FileInputStream fs = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet workSheet = workbook.getSheetAt(0);
		int totalRowCount = workSheet.getLastRowNum() + 1;
		int totalColoumnCount = workSheet.getRow(0).getLastCellNum();
		Iterator<Row> row = workSheet.rowIterator();
		int column_num = 0;
		for (int i = 0; i < totalColoumnCount; i++) {
			if (workSheet.getRow(0).getCell(i).getStringCellValue().trim().equals("InputDataValue"))
				column_num = i;
		}

		if (workSheet.getRow(col2RowValue).getCell(column_num).getCellTypeEnum() == CellType.STRING) {
			return workSheet.getRow(col2RowValue).getCell(column_num).toString();
		} else {
			return NumberToTextConverter
					.toText(workSheet.getRow(col2RowValue).getCell(column_num).getNumericCellValue());
		}

	}

	@AfterSuite
	public void tearDown() {
	//	driver.quit();
		extent.flush();

	}
}
