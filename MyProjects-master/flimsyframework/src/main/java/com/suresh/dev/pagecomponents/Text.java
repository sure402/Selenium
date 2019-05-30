package com.suresh.dev.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.TextLocators;
import com.suresh.dev.utils.ActionUtility;


public class Text extends TextLocators{
	

	public Text(WebDriver driver, String url) {
		super(driver, url);
	}
	public Text(WebDriver driver) {
		super(driver);
	}	

	
	public boolean checkContent(By locator, String compare) {
		boolean flag = false;
		try {
			if (ActionUtility
					.getElement(driver, locator).getText().contains(compare))  
				flag = true;
		} catch (Exception e) {
			throw new RuntimeException("GetElement and getting text from "
					+ locator.toString() + " failed: " + e.getMessage());
		}
		return flag;
	}
	
	
	public boolean getContent(By locator) {
		boolean flag = true;
		try {
			if (ActionUtility
					.getElement(driver, locator).getAttribute("value").isEmpty())
				flag = false;
		} catch (Exception e) {
			throw new RuntimeException("GetElement and getting text from "
					+ locator.toString() + " failed: " + e.getMessage());
		}
		return flag;
	}
	
	
	public boolean getContentForMultipleElements(By locator) {
		boolean flag = true;
		List<WebElement> dataSets = driver.findElements(locator);
	    try {
	    	for (WebElement eachData : dataSets){
	    		if(ActionUtility.getElement(driver, locator).getText().isEmpty()){
	    			flag = false;
	    			System.out.println(eachData);
	    			break;  
	    		}
	    	}
	    }catch (Exception e) {
	    	throw new RuntimeException("GetElement and getting text from "
					+ locator.toString() + " failed: " + e.getMessage());
		}    
		return flag;
	}
	
	
	public boolean getContentForMultipleElementsByAttribute(By locator, String attr) {
		boolean flag = true;
		List<WebElement> dataSets = driver.findElements(locator);
	    try {
	    	for (WebElement eachData : dataSets){
	    		if(ActionUtility.getElement(driver, locator).getAttribute(attr).isEmpty()){
	    			flag = false;
	    			System.out.println(eachData);
	    			break;  // content not seen 
	    		}
	    	}
	    }catch (Exception e) {
	    	throw new RuntimeException("GetElement and getting text from "
					+ locator.toString() + " failed: " + e.getMessage());
		}    
		return flag;
	}

	
	public String getContentByXpath(By locator) {
		return ActionUtility.waitAndGetText(driver, locator);
	}
	
}
