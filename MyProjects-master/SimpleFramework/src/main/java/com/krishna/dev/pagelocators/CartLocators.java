package com.krishna.dev.pagelocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.krishna.dev.baseconfig.BaseConfig;

/**
 * @author kabothu
 *
 */
public class CartLocators extends BaseConfig {

	public CartLocators(WebDriver driver) {
		super(driver);
	}

	public CartLocators(WebDriver driver, String url) {
		super(driver, url);
	}

	public final By TITLE_BEFORE_ADDING_ITEM = By.cssSelector("h1>span");
	public final By SELECT_ITEMS_FROM_MODULE = By.xpath("//div/div/li[1]/div/a/img");
	public final By SELECT_ASPECT = By.cssSelector(
			".variants.variants-swatches.js-variants-swatches.js-variants-collapsed.variants-collapsed>span:nth-of-type(1)");
	public final By ADD_ITEM_CART = By.id("WMItemAddToCartBtn");
	public final By VIEW_ITEM_CART = By.id("PACViewCartBtn");
	public final By CHECK_ADDED_ITEM_IN_CART = By.xpath("//div[3]/div/a/span");

}
