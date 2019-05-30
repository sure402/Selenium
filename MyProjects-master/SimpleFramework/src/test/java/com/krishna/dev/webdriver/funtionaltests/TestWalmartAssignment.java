package com.krishna.dev.webdriver.funtionaltests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.krishna.dev.dataproviders.DataProviders;
import com.krishna.dev.pagecomponents.Cart;
import com.krishna.dev.pagecomponents.SelectingItem;
import com.krishna.dev.utils.PropertyFileUtility;
import com.krishna.dev.utils.TesterInfo;
import com.krishna.dev.webdriver.basetest.BaseTest;

/**
 * @author kabothu
 *
 */
public class TestWalmartAssignment extends BaseTest {

	private final SelectingItem selectItem = new SelectingItem(driver);
	private final Cart cart = new Cart(driver);

	@TesterInfo(OWNER = "kabothu")
	@Test(dataProvider = "keywords", dataProviderClass = DataProviders.class)
	public void testAddItemTransactionFlow(String keywords) {

		selectItem.retryingEnteringText(driver, selectItem.KEYWORDS_TO_BE_SEARCHED, keywords);
		selectItem.retryingClickingSearchButton(driver, selectItem.CLICK_SEARCH_BUTTON);
		selectItem.selectingAnItemFromSearchResults(driver, selectItem.SELECTING_AN_ITEM_FROM_SEARCH_RESULTS);
		String titleBeforeAddingToCart = cart.titleBeforeAddingToCart(driver, cart.TITLE_BEFORE_ADDING_ITEM);
		cart.selectItemFromListOfModulesInLeftHandSide(driver, cart.SELECT_ITEMS_FROM_MODULE);
		cart.selectAspectForAnItem(driver, cart.SELECT_ASPECT);
		cart.addItemToCart(driver, cart.ADD_ITEM_CART);
		cart.viewItemCart(driver, cart.VIEW_ITEM_CART);
		String validateAddedItemTitleInCart = cart.checkAddedItemInCart(driver, cart.CHECK_ADDED_ITEM_IN_CART);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals("Added Item is not same as the Item Present in Cart", validateAddedItemTitleInCart,
				titleBeforeAddingToCart);
		softAssert.assertAll();

	}

}
