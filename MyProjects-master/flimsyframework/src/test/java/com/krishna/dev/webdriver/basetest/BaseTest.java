package com.krishna.dev.webdriver.basetest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.suresh.dev.pagecomponents.LoginPage;
import com.suresh.dev.pagecomponents.TextField;
import com.suresh.dev.pagelocators.LoginPageLocators;
import com.suresh.dev.utils.ActionUtility;
import com.suresh.dev.utils.PropertyFileUtility;
import com.suresh.dev.utils.WaitForUtility;

public class BaseTest {

	protected static WebDriver driver;
	protected TextField textField = new TextField(driver);
	protected LoginPage loginPage = new LoginPage(driver);
	private PropertyFileUtility propertyFileUtils = new PropertyFileUtility();

	@BeforeClass
	public void launchApplication() {
		driver = new FirefoxDriver();
		LoginPage.open(driver, propertyFileUtils.getPropertyValue("url"));
		WaitForUtility.waitForSeconds(driver, 20);

	}

	@BeforeMethod
	public void loginPage() {
		textField.enterText(driver, loginPage.NAME, propertyFileUtils.getPropertyValue("username"));
		textField.enterText(driver, loginPage.PASSWORD, propertyFileUtils.getPropertyValue("password"));
		ActionUtility.waitAndClick(driver, loginPage.LOGIN_BUTTON);

	}

	@AfterMethod
	public void logoutApplication() {
		ActionUtility.waitAndClick(driver, loginPage.LOGOUT_BUTTON);
	}

	@AfterClass
	public void exitBrowser() {
		driver.quit();
	}

}
