package com.suresh.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.baseconfig.BaseConfig;

public class XDeleteOrCloseLocators extends BaseConfig {
	
	public XDeleteOrCloseLocators(WebDriver driver) {
		super(driver);
	}
	public XDeleteOrCloseLocators(WebDriver driver, String url) {
		super(driver, url);
	}
	public final By NAME = By.name("username");
	public final By PASSWORD = By.name("pwd");
	public final By LOGIN_BUTTON = By.id("loginButton");

}
