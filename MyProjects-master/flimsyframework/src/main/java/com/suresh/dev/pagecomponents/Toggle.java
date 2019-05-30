package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.pagelocators.ToggleLocators;
import com.suresh.dev.utils.ActionUtility;


public class Toggle extends ToggleLocators {
	

	public Toggle(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public Toggle(WebDriver driver) {
		super(driver);
	}
	
	
	public void toggleSelection(By locator) {	
		try {
			ActionUtility.waitAndClick(driver,locator);
		} catch (Exception e) {
		      throw new RuntimeException("Failed Setting Yes on toggle : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	
	public boolean isToggleSelectionSaved(By locator){
		try {
			ActionUtility.getElement(driver, locator);
			return true;
		} catch (Exception e) {	
	    	return false;  
	    } 
	}
	
}
