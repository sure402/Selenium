package com.suresh.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suresh.dev.baseconfig.BaseConfig;

public class LoginPageLocators extends BaseConfig {

	public LoginPageLocators(WebDriver driver) {
		super(driver);
	}

	public LoginPageLocators(WebDriver driver, String url) {
		super(driver, url);
	}

	public final By NAME = By.name("username");
	public final By PASSWORD = By.name("pwd");
	public final By LOGIN_BUTTON = By.id("loginButton");
	public final By LOGOUT_BUTTON = By.className("logout");

}
