package com.krishna.dev.pagecomponents;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.krishna.dev.pagelocators.CartLocators;
import com.krishna.dev.utils.PropertyFileUtility;

/**
 * @author kabothu
 *
 */
public class Cart extends CartLocators {

	protected PropertyFileUtility propertyFileUtils = new PropertyFileUtility();

	public Cart(WebDriver driver) {
		super(driver);
	}

	/**
	 * @param driver
	 * @param locator
	 * @return
	 */
	public String titleBeforeAddingToCart(WebDriver driver, By locator) {

		String titleBeforeAddingToCart = "";
		try {
			titleBeforeAddingToCart = driver.findElement(locator).getText();
		} catch (Exception e) {
			throw new RuntimeException(
					"GetElement and entering text in " + locator.toString() + " failed: " + e.getMessage());
		}
		return titleBeforeAddingToCart;
	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void selectItemFromListOfModulesInLeftHandSide(WebDriver driver, By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException("Selecting Item From List of Modules In Left Side " + locator.toString()
					+ " failed: " + e.getMessage());
		}

	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void selectAspectForAnItem(WebDriver driver, By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException(
					"Selecting Aspect For An Item " + locator.toString() + " failed: " + e.getMessage());
		}

	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void addItemToCart(WebDriver driver, By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException("Adding Item To Cart " + locator.toString() + " failed: " + e.getMessage());
		}

	}

	/**
	 * @param driver
	 * @param locator
	 */
	public void viewItemCart(WebDriver driver, By locator) {

		try {
			Set<String> setOfWindowHandles = driver.getWindowHandles();
			Iterator<String> iterator = setOfWindowHandles.iterator();
			String currentWindowHandle = iterator.next();
			String itemTextInOverLayPopup = iterator.next();
			driver.switchTo().window(itemTextInOverLayPopup);
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new RuntimeException("Viewing Item To Cart " + locator.toString() + " failed: " + e.getMessage());
		}

	}

	/**
	 * @param driver
	 * @param locator
	 * @return
	 */
	public String checkAddedItemInCart(WebDriver driver, By locator) {

		String addedItemInCart = "";
		try {
			addedItemInCart = driver.findElement(locator).getText();
		} catch (Exception e) {
			throw new RuntimeException("Viewing Item To Cart " + locator.toString() + " failed: " + e.getMessage());
		}
		return addedItemInCart;

	}

}
