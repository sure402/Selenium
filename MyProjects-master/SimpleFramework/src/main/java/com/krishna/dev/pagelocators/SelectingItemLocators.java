package com.krishna.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.krishna.dev.baseconfig.BaseConfig;

/**
 * @author kabothu
 *
 */
public class SelectingItemLocators extends BaseConfig {

	public SelectingItemLocators(WebDriver driver) {
		super(driver);
	}

	public SelectingItemLocators(WebDriver driver, String url) {
		super(driver, url);
	}

	public final By KEYWORDS_TO_BE_SEARCHED = By.id("search");
	public final By CLICK_SEARCH_BUTTON = By.cssSelector(".searchbar-submit.js-searchbar-submit");
	public final By SELECTING_AN_ITEM_FROM_SEARCH_RESULTS = By.xpath("//*[@id='tile-container']/div[1]/a/img");
	

}
