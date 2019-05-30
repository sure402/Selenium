package com.krishna.dev.webdriver.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.krishna.dev.pagecomponents.Login;
import com.krishna.dev.utils.PropertyFileUtility;
import com.krishna.dev.utils.WaitForUtility;

/**
 * @author kabothu
 *
 */
public class BaseTest {

	protected static WebDriver driver;
	private final Login loginPage = new Login(driver);
	private final PropertyFileUtility propertyFileUtils = new PropertyFileUtility();

	@BeforeClass
	public void launchApplication() {
		String currentDir = System.getProperty("user.dir");
		String filePath = (currentDir + "/src/main/resources/com/krishna/driverDependencies/chromedriver.exe").replace('\\', '/');
		System.setProperty("webdriver.chrome.driver", filePath);
		driver = new ChromeDriver();
		Login.open(driver, propertyFileUtils.getPropertyValue("url"));
		WaitForUtility.waitForSeconds(driver, 20);

	}

	@BeforeMethod
	public void loginPage() {
		
		Login.enterUserNameAndPassword(driver, loginPage.NAME, propertyFileUtils.getPropertyValue("username"));
		Login.enterUserNameAndPassword(driver, loginPage.PASSWORD,propertyFileUtils.getPropertyValue("password"));
		Login.clickLoginButton(driver, loginPage.LOGIN_BUTTON);
		WaitForUtility.waitForSeconds(driver, 20);

	}

	@AfterMethod
	public void logoutApplication() {
		Login.logoutURL(driver, propertyFileUtils.getPropertyValue("logouturl"));
	}

	@AfterClass
	public void exitBrowser() {
		driver.quit();
	}

}
