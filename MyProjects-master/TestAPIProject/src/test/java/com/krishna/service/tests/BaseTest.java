package com.krishna.service.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.krishna.services.util.ExtentManager;
import com.krishna.services.util.LoggerHelper;
import com.krishna.services.util.ResourceHelper;

/**
 * Base class which needs to be extended by every test class file
 * 
 *  
 * @author kabothu 
 */
public class BaseTest {

	private Logger logger = LoggerHelper.getLogger(BaseTest.class);
	public static ExtentReports extent;
	public static ExtentTest test;
	public static File reportDirectory;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent = ExtentManager.getInstance();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		reportDirectory = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
		test = extent.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + "**************test started***************");
		logger.info("**************" + method.getName() + "Started***************");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		logger.info("**************" + result.getName() + "Finished***************");
		extent.flush();
	}

}
