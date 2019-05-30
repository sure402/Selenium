package com.krishna.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.krishna.dev.pagelocators.LoginLocators;

/**
 * @author kabothu
 *
 */
public class Login extends LoginLocators {

	public Login(WebDriver driver) {
		super(driver);
	}

	/**
	 * @param driver
	 * @param url
	 */
	public static void open(final WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * @param driver
	 * @param url
	 */
	public static void logoutURL(final WebDriver driver, String url) {
		driver.get(url);
	}
	
	/**
	 * @param driver
	 * @param locator
	 */
	public static void clickLoginButton(WebDriver driver, By locator) {
		try {
		   driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException(
					"Clicking on Login Button for " + locator.toString() + " failed: " + e.getMessage());
		}
	}

	/**
	 * @param driver
	 * @param locator
	 * @param data
	 */
	public static void enterUserNameAndPassword(WebDriver driver, By locator, String data) {
		try {
			WebElement element = driver.findElement(locator);
			element.click();
			element.clear();
			element.sendKeys(data);
		} catch (Exception e) {
			throw new RuntimeException(
					"GetElement and entering text in " + locator.toString() + " failed: " + e.getMessage());
		}
	}

}
