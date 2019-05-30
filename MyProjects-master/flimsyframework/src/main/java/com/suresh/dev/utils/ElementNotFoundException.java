package com.suresh.dev.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ElementNotFoundException extends RuntimeException {

	public ElementNotFoundException(By locator,String failure){
		super("\n"+ failure +"\n Could not find element using "+locator);
	}
	public ElementNotFoundException(By locator, String failure, WebDriver driver) {
	    super("\n" + failure + " \n Could not find element using " + locator + " \n" + ActionUtility.captureScreenShot(driver));
	  }

	  public ElementNotFoundException(String failure, WebDriver driver) {
	    super("\n" + failure + " \n Could not find element.\n" + ActionUtility.captureScreenShot(driver));
	  }
}
