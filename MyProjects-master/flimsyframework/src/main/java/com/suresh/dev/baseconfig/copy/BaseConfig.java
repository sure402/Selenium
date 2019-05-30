package com.suresh.dev.baseconfig.copy;

import org.openqa.selenium.WebDriver;

public class BaseConfig {

	public WebDriver driver;
	public String url;

	public BaseConfig(WebDriver driver) {
		this.driver = driver;
	}

	public BaseConfig(WebDriver driver, String url) {
		this.url = url;
		this.driver = driver;
	}

}
