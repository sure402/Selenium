package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.ExpandLocators;
import com.suresh.dev.utils.ActionUtility;


public class Expand extends ExpandLocators{

	public Expand(WebDriver driver, String url) {
		super(driver, url);
	}
	public Expand(WebDriver driver) {
		super(driver);
	}	
	
	
	public void clickExpand(By locator) {	
	    try {
	    	ActionUtility.waitAndClick(driver, locator);
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the expand icon : " + locator.toString()
			          + e.getMessage());
		}    
	}
	public void expandParticularCategoryTree(By locator, String attrName, String attrValue) {
		String formattedLocator=String.format(locator.toString(), attrValue);
	    	WebElement category = driver.findElement(locator);
	    	category.click();
	}
}
