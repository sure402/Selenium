package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.pagelocators.RadioButtonLocators;
import com.suresh.dev.utils.ActionUtility;

/**
 * Component to handle buttons on pages within kiosk
 * @author lseshadri
 *
 */
public class RadioButton extends RadioButtonLocators{

	public RadioButton(WebDriver driver, String url) {
		super(driver, url);
	}
	public RadioButton(WebDriver driver) {
		super(driver);
	}	
	
	public void selectRadioButton(By locator) {	
	    try {
	    	ActionUtility.waitAndClick(driver, locator);
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the radio button option: " + locator.toString()
			          + e.getMessage());
		}
	}

	public void selectYesRadioButton(String value) {	
	    try {
	    	ActionUtility.waitAndClick(driver, By.xpath("//span[@val='" + value + "']"));
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the radio button option: " + value
			          + e.getMessage());
		}
	}
}
