package com.krishna.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.krishna.dev.baseconfig.BaseConfig;

/**
 * @author kabothu
 *
 */
public class LoginLocators extends BaseConfig {

	public LoginLocators(WebDriver driver) {
		super(driver);
	}

	public LoginLocators(WebDriver driver, String url) {
		super(driver, url);
	}

	public final By NAME = By.id("login-username");
	public final By PASSWORD = By.id("login-password");
	public final By LOGIN_BUTTON = By
			.cssSelector(".btn.login-sign-in-btn.js-login-sign-in.btn-block-max-s.btn-block-max-s");

}
