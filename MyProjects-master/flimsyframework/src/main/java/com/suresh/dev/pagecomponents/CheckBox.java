package com.suresh.dev.pagecomponents;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.CheckBoxLocators;
import com.suresh.dev.utils.ActionUtility;


public class CheckBox extends CheckBoxLocators{

	public CheckBox(WebDriver driver, String url) {
		super(driver, url);
	}
	public CheckBox(WebDriver driver) {
		super(driver);
	}	
	
	
	public void selectCheckBox(By locator) {	
	    try {
	    	ActionUtility.waitAndClick(driver, locator);
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the check box option : " + locator.toString()
			          + e.getMessage());
		}    
	}

	public void selectCheckBoxByAttrValue(By locator, String attr, String[] attrVal) {	
	    try {
	    	List<WebElement> listOfChkBoxes = driver.findElements(locator);
	    	for(int i=0; i<attrVal.length; i++){  // for all the values specified
	    		for(WebElement checkBox: listOfChkBoxes){ //for all the locator compare the value and if match found click
	    			if(checkBox.getAttribute(attr).contains(attrVal[i])){
	    				ActionUtility.click(checkBox);
	    			}
	    		}
	    	}
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the check box option : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	
	public void selectMultipleCheckBox(By locator, List<String> itemList) {
		List<WebElement> listOfItems = driver.findElements(locator);
	    try {
	    	for (int i=0; i<itemList.size(); i++) 	{  
	    		int num = Integer.parseInt((String)itemList.get(i)) ;
	    		ActionUtility.click((WebElement)listOfItems.get(num));
	    	}
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the multiple check box options : " + locator.toString()
			          + e.getMessage());
		}    
	}
	
	public void selectAllCheckBoxByLocator(By locator) {
		List<WebElement> listOfItems = driver.findElements(locator);
	    try {
	    	for(WebElement chk : listOfItems){
	    		ActionUtility.click(chk);
	    	}
	    }catch (Exception e) {
		      throw new RuntimeException("Failed clicking on the multiple check box options : " + locator.toString()
			          + e.getMessage());
		}    
	}

	public void selectACheckBox(String value) {
		ActionUtility.waitAndClick(driver, By.xpath("//input[@title='"+ value +"']"));
	}
	
	
	public boolean isChecked(WebElement element)
	{
		return element.getAttribute("aria-selected")!=null;
	}
	
	public void selectACheckBox(List<WebElement> elements,int index)
	{
		int loopCount=0;
		for(WebElement element:elements)
		{
			if(!isChecked(element) && loopCount==index)
			{
				element.click();
			}
			else if(isChecked(element))
			{
				element.click();  //unchecking
			}
			loopCount++;
		}
	}
	

}
