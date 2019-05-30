package com.krishna.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import com.krishna.dev.pagelocators.SelectingItemLocators;
import com.krishna.dev.utils.WaitForUtility;

/**
 * @author kabothu
 *
 */
public class SelectingItem extends SelectingItemLocators {


	public SelectingItem(WebDriver driver) {
		super(driver);
	}

	/**
	 * @param driver
	 * @param locator
	 * @param data
	 */
	public void enterKeywordsToBeSearched(WebDriver driver, By locator,String data) {
		try {
			WaitForUtility.waitForSeconds(driver, 20);
			driver.findElement(locator).sendKeys(data);
		} catch (Exception e) {
			throw new RuntimeException(
					"GetElement and entering text in " + locator.toString() + " failed: " + e.getMessage());
		}
	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void clickSearchButton(WebDriver driver, By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException(
					"Clicking on SearchButton for " + locator.toString() + " failed: " + e.getMessage());
		}

	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void selectingAnItemFromSearchResults(WebDriver driver, By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException(
					"Selecting an Item From Search Results for " + locator.toString() + " failed: " + e.getMessage());
		}

	}

	
	/**
	 * @param driver
	 * @param locator
	 */
	public void retryingClickingSearchButton(WebDriver driver, By locator) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(locator).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	/**
	 * @param driver
	 * @param locator
	 * @param data
	 */
	public void retryingEnteringText(WebDriver driver, By locator,String data) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(locator).sendKeys(data);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

}
