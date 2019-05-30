package com.krishna.dev.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * @author kabothu
 *
 */
public class ElementNotFoundException extends RuntimeException {

	public ElementNotFoundException(By locator,String failure){
		super("\n"+ failure +"\n Could not find element using "+locator);
	}
	public ElementNotFoundException(By locator, String failure, WebDriver driver) {
	    super("\n" + failure + " \n Could not find element using " + locator + " \n" );
	  }

	  public ElementNotFoundException(String failure, WebDriver driver) {
	    super("\n" + failure + " \n Could not find element.\n" );
	  }
}
