package com.suresh.dev.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.ButtonsLocators;
import com.suresh.dev.utils.ActionUtility;
import com.suresh.dev.utils.WaitForUtility;


public class Buttons extends ButtonsLocators {

	public Buttons(WebDriver driver, String url) {
		super(driver, url);
	}
	public Buttons(WebDriver driver) {
		super(driver);
	}	
	
	
	public void clickButtons(By locator) {	
	    try {
	    	ActionUtility.waitAndClick(driver, locator);
	    	WaitForUtility.waitForSeconds(driver, 5);
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the action button : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	
	public void clickButtonByLocation(By locator, int location) {	
	    try {
	    	List<WebElement> buttons =ActionUtility.getAllElementsWithSameLocator(driver, locator);
	    	for(int i=0; i<buttons.size(); i++){
				if(i==location){
					((WebElement)buttons.get(i)).click();	
					break;
				}
			}
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the action button : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	public void clickButtonByIndex(By locator, int location) {	
	    try {
	    	List<WebElement> buttons =driver.findElements(locator);
	    	buttons.get(location).click();
			}
	    catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the action button : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	
}
