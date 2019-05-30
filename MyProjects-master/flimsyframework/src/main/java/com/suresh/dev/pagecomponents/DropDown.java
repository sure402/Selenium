package com.suresh.dev.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.DropDownLocators;
import com.suresh.dev.utils.ActionUtility;
import com.suresh.dev.utils.WaitForUtility;


public class DropDown extends DropDownLocators {
	
	public DropDown(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public DropDown(WebDriver driver) {
		super(driver);
	}
	
	
	public void selectOptionWithSearch(By locator, int type) {
		String searchStr = null;
		switch (type) {
		case 0: 
			searchStr = "//label[contains(@for, 'curationId') and contains(@title, 'Auto')] ";
			break;
		case 1: 
			searchStr = "//label[contains(@for, 'eventGroup') and contains(@title, 'Auto')] ";
			break;
	
		}
		try {
			ActionUtility.waitAndClick(driver, locator);
			List<WebElement> options = driver.findElements(By.xpath(searchStr));
			for (WebElement option : options) {
				if (option.getAttribute("title").contains("Auto")) {
					option.click();
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed selecting from a drop down : "
					+ locator.toString() + e.getMessage());
		}
	}
	
	public void selectOptionByIndex(By locator, int type, int idx, String txt) {
		String searchStr = null;
		switch (type) {
		case 0: 
			searchStr = "//label[contains(@for, 'curationId') and contains(@title, '"+txt+"')] ";
			break;
		case 1: 
			searchStr = "//label[contains(@for, 'eventGroup') and contains(@title, '"+txt+"')] ";
			break;
		}
		try {
			ActionUtility.waitAndClick(driver, locator);
			List<WebElement> options = driver.findElements(By.xpath(searchStr));
			options.get(0).click();
		} catch (Exception e) {
			throw new RuntimeException("Failed selecting from a drop down : "
					+ locator.toString() + e.getMessage());
		}
	}
	
	
	public void selectOption(By locator, String selectionTxt) {
		try {
			List<WebElement> options = driver.findElements(locator);
			for (WebElement option : options) {
				if (selectionTxt.trim().equals(option.getText().trim())) {
					ActionUtility.click(option);
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed selecting from a drop down : "
					+ locator.toString() + e.getMessage());
		}
	}

	public void selectOptionByLocator(By locator, By selectLocator) {
		ActionUtility.highlightElement(driver, locator);
			ActionUtility.waitAndClick(driver, locator);
			ActionUtility.highlightElement(driver, selectLocator);
			ActionUtility.waitAndClick(driver, selectLocator);
			
	}
	public void selectOptionFromRegularDropDown(By selectLocator, By optionLocator) {
		ActionUtility.selectOptionFromDropDown(driver, selectLocator, ActionUtility.getElement(driver, optionLocator).getText());
		WaitForUtility.waitForSeconds(driver, 3);
		
	}
	
	
	public void selectOptionContainsText(By locator, String selectionTxt) {
		try {
			ActionUtility.waitAndClick(driver, locator);
			List<WebElement> options = driver.findElements(locator);
			for (WebElement option : options) {
				if (option.getText().contains(selectionTxt)) {
					ActionUtility.click(option);
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed selecting from a drop down : "
					+ locator.toString() + e.getMessage());
		}
	}
	
	
	public boolean isSelectionSaved(By locator) {
		boolean flag = true;
		try {
			if (ActionUtility.getElement(driver, locator).getText().isEmpty())
				flag = false;

		} catch (Exception e) {
			throw new RuntimeException(
					"Selection from a drop down not saved : "
							+ locator.toString() + e.getMessage());
		}
		return flag;
	}
	
	
	public boolean isSelectionSaved(By locator,String selectedOptionTxt) {
		boolean isSelected = false;
		try {
			List<WebElement> options = ActionUtility.getAllElementsWithSameLocator(driver, locator);
		    for (WebElement option : options) {
		      if (option.isSelected() && option.getText().trim().equals(selectedOptionTxt.trim())) {
		    	  isSelected=true;
		      }
		    }
		} catch (Exception e) {
			throw new RuntimeException(
					"Selection from a drop down not saved : "
							+ locator.toString() + e.getMessage());
		}
		return isSelected;
	}
	
		
}
