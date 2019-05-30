package com.krishna.dev.utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * @author kabothu
 *
 */
public class WaitForUtility {

	static long TIMEOUT_S = 60;
	static int INT_TIMEOUT_S = Integer.parseInt(Long.toString(TIMEOUT_S));
	static long WAIT_TIMEOUT = 30L;
	static long WAIT_TIMEOUT_FOR_URL = 15L;
	final static Logger logger = Logger.getLogger(WaitForUtility.class.getName());

	protected static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}

	/**
	 * Waits for the element to be visible until a timeout of 30 secs.
	 * 
	 * @param driver
	 * @param locator
	 */
	public static void waitForElementToBeVisible(final WebDriver driver, final By locator) throws RuntimeException {
		Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
		try {
			wait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement element = driver.findElement(locator);
					if (element.isDisplayed()) {
						return element;
					}
					return null;
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * Waits for the element to be selected until a timeout of 30 secs.
	 * 
	 * @param driver
	 * @param locator
	 */
	public static void waitForElementToBeSelected(final WebDriver driver, final By locator, final long pollMax)
			throws RuntimeException {
		Wait<WebDriver> wait = new WebDriverWait(driver, pollMax);
		try {
			wait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					// driver.switchTo().defaultContent();
					WebElement element = driver.findElement(locator);
					if (element.isSelected()) {
						return element;
					}
					return null;
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * Waits for the given text until timing out at 30 secs.
	 * 
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void waitForText(final WebDriver driver, final By locator, final String text) {
		Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					String currentText = "";
					try {
						currentText = driver.findElement(locator).getText();
					} catch (Exception e) {
						// ignore if element is not present.
					}
					logger.info("Waiting for:" + text + " Found:" + currentText);
					return currentText.contains(text);
				}
			});

		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while waiting for text " + text + " in " + locator + ". Exception:" + e);
		}
	}

	public static void holdUntil(long milliSeconds) {
		long waitUntilTime = System.currentTimeMillis() + (milliSeconds);
		while (System.currentTimeMillis() < waitUntilTime) {
		}
	}

	public static void waitForSeconds(WebDriver driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

}
