package com.suresh.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.baseconfig.BaseConfig;

public class TextFieldLocators extends BaseConfig {
	
	public TextFieldLocators(WebDriver driver) {
		super(driver);
	}
	public TextFieldLocators(WebDriver driver, String url) {
		super(driver, url);
	}
	public final By NAME = By.name("username");
	public final By PASSWORD = By.name("pwd");
	public final By LOGIN_BUTTON = By.id("loginButton");

}
