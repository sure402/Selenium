package com.suresh.dev.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.pagelocators.TabNavigationLocators;
import com.suresh.dev.utils.ActionUtility;


public class TabNavigation extends TabNavigationLocators {
	public TabNavigation(WebDriver driver, String url) {
		super(driver, url);
	}
	public TabNavigation(WebDriver driver) {
		super(driver);
	}
	
	
	public String getTabs(By locator) {
		StringBuffer tabList = null;
		try {
			List<WebElement> tabs = ActionUtility.getAllElementsWithSameLocator(driver, locator);
			tabList = new StringBuffer();
			for (WebElement tab : tabs) {
				tabList.append(tab.getText());
			}
		} catch (Exception e) {
			throw new RuntimeException("getting Tab list failed"
					+ e.getMessage());
		}
		return tabList.toString();
	}
	
	
	public void clickTab(String tabToClick, By locator) {
		try {
			List<WebElement> tabList = ActionUtility.getAllElementsWithSameLocator(driver, locator);
			for (WebElement tab : tabList) {
				if (tab.getText().contains(tabToClick)) {
					tab.click();
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Clicking on Tab Failed"
					+ e.getMessage());
		}
	}
}
