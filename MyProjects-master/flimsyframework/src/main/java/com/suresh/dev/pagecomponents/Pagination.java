package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.pagelocators.PaginationLocators;
import com.suresh.dev.utils.ActionUtility;



public class Pagination extends PaginationLocators {
	public Pagination(WebDriver driver, String url) {
		super(driver, url);
	}

	public Pagination(WebDriver driver) {
		super(driver);
	}
	
	
	public void clickPreviousPage(By locator)
	{
		try {
			ActionUtility.waitAndClick(driver, locator);
		} catch (Exception e) {
			throw new RuntimeException("Clicking on previous failed."
					+ e.getMessage());
		}
		
	}
	
	public void clickNextPage(By locator)
	{
		try {
			ActionUtility.waitAndClick(driver, locator);
		} catch (Exception e) {
			throw new RuntimeException("Clicking on next page failed."
					+ e.getMessage());
		}
		
		
	}
	public void clickPage(String pageNumber)
	{
		final String pageToClickLocator = ".pagn>a[href *='pgn=" + pageNumber + "']";
		ActionUtility.click(driver.findElement(By.cssSelector(pageToClickLocator)));
	}
}
