package com.suresh.dev.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.TextFieldLocators;
import com.suresh.dev.utils.ActionUtility;


public class TextField extends TextFieldLocators {
	

	public TextField(WebDriver driver, String url) {
		super(driver, url);
	}
	public TextField(WebDriver driver) {
		super(driver);
	}
	
	public void hoverOver(By locator, String data){
		WebElement element = ActionUtility.getElement(driver, locator);
		element.click();
		element.sendKeys(data);
	}
	
	public void hoverOnTitle(By locator){
		ActionUtility.mouseOver(ActionUtility.getElement(driver, locator), By.xpath("//*[@class='ceg-page-title']/textarea"), driver);
	}
	
	
	
	public void enterText(WebDriver driver,By locator, String data) {	
		
	    try {
	    	WebElement element = ActionUtility.getElement(driver, locator);
	    	element.click();
	    	element.clear();
	    	element.sendKeys(data);
	    } catch (Exception e) {
		      throw new RuntimeException("GetElement and entering text in "+ locator.toString() +" failed: " 
		          + e.getMessage());
		    }   
		}
	
	
	public void enterTextByLocation(By locator, String data, int location) {	
	    try {
	    	List<WebElement> textfields =ActionUtility.getAllElementsWithSameLocator(driver, locator);
	    	for(int i=0; i<textfields.size(); i++){
				if(i==location){
					((WebElement)textfields.get(i)).click();
					((WebElement)textfields.get(i)).clear();
					((WebElement)textfields.get(i)).sendKeys(data);
					break;
				}
			}
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the action button : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	public void enterTextByIndex(By locator, String data, int location) {	
	    try {
	    	List<WebElement> textfields =driver.findElements(locator);
	    	textfields.get(location).clear();
	    	textfields.get(location).sendKeys(data);
			}
	    catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the action button : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	
	public String getSavedTextInTextArea(By locator) {
		return ActionUtility.waitAndGetText(driver, locator);
	}
	
	
	public String getInputValue(By locator) {
		return ActionUtility.getAttributeFromElement(driver, locator, "value");
	}
	
	
	public boolean getSavedTextInInput(By locator) {
		return ActionUtility.getAttributeFromElement(driver, locator, "value") != null;
	}
	
	
	public boolean getSavedTextInMultipleInput(By locator, String compare) {
		boolean flag = true;
		List<WebElement> listOfCategories = driver.findElements(locator);
	    try {
	    	for (int i=0; i<listOfCategories.size()-1; i++){
	    		String catText = listOfCategories.get(i).getAttribute("value");
	    		if(catText.isEmpty() || catText.contains(compare)){
	    			flag = false;
	    			break;  // category not seen on the custom category textfield which is a failure
	    		}
	    	}
	    }catch (Exception e) {
	    	throw new RuntimeException("GetElement and getting text from "
					+ locator.toString() + " failed: " + e.getMessage());
		}    
		return flag;
	}
}
