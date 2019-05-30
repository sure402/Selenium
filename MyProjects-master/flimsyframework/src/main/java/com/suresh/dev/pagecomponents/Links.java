package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.pagelocators.LinksLocators;
import com.suresh.dev.utils.ActionUtility;


public class Links extends LinksLocators {
	

	public Links(WebDriver driver, String url) {
		super(driver, url);
	}
	public Links(WebDriver driver) {
		super(driver);
	}
	
	
	public void clickOnLink(By locator) {	
		try{
			ActionUtility.mouseOver(locator, locator, driver);
			ActionUtility.getElement(driver, locator).click();	    		
	    } catch (Exception e) {
	      throw new RuntimeException("GetElement and clicking "+ locator.toString() +" failed: " 
	          + e.getMessage());
	    }   
	}
}
