package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.LoginPageLocators;
import com.suresh.dev.utils.ActionUtility;
import com.suresh.dev.utils.WaitForUtility;

public class LoginPage extends LoginPageLocators {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public static void open(final WebDriver driver, String url) {
		driver.get(url);
	}

	public void clickOnEditIcon(By locator) {
		try {
			ActionUtility.waitAndClick(driver, locator);
		} catch (Exception e) {
			throw new RuntimeException("Edit icon could not be found and clicked." + e.getMessage());
		}
	}

	public void clearDefaultTextAndEnterName(By locator, String name) {

		try {
			ActionUtility.clearFieldAndSendText(driver, locator, name);
		} catch (Exception e) {
			throw new RuntimeException("Clearing default text and entering name did not work." + e.getMessage());
		}
	}

	public String getSavedText(By locator) {
		String str = null;
		try {
			str = ActionUtility.getElement(driver, locator).getText();

		} catch (Exception e) {
			throw new RuntimeException(
					"GetElement and getting text from " + locator.toString() + " failed: " + e.getMessage());
		}
		return str;
	}

	public static WebElement getElement(WebDriver driver, By locator) throws RuntimeException {
		WaitForUtility.waitForElementToBeVisible(driver, locator);
		return driver.findElement(locator);
	}

}
