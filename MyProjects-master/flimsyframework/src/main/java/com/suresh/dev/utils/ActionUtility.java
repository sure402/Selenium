package com.suresh.dev.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.common.base.Function;


public class ActionUtility {

  private static final String OPEN = "<i><b>Loaded</i></b> ";
  private static final String CLICK_EVENT = "<i><b>Clicked</i></b> ";
  private static final String TYPED = "<i><b>Typed</i></b> ";
  private static final String CLEARED = "<i><b>Cleared</i></b> ";
  private static final String SUBMITTED = "<i><b>Submitted</i></b> ";
  private static final String SEPARATOR = " - ";
  private static final String HOVERED = "<i><b>Hovered</i></b> ";
  private static final String CURRENT_URL = "<i><b>Current url is:</i></b>";
  private static final String SWITCHED_TO_IFRAME = "<b>Switched to iframe:</b>";
  private static final String FOUND = " found ";
  private static final String COOKIES = " cookies ";
  private static final String ADDED = " Added ";
  private static final long WAIT_TIMEOUT = 30L;

  private ActionUtility() {
  }

  /**
 * @param driver
 * @param url
 */
public static void open(final WebDriver driver, String url) {
    driver.get(url);
    logStep(OPEN + url);
  }

  /**
   * Sets cookie to the current browser instance.
   * 
   * @param driver
   *          current browser instance
   * @param cookieName
   *          cookie that has to be added.
   * @param cookieValue
   *          value of the cookie to be added.
   */
  public static void setCookie(WebDriver driver, String cookieName,
      String cookieValue) {
    Cookie cookie = new Cookie(cookieName, cookieValue);
    driver.manage().addCookie(cookie);
    logStep(ADDED + COOKIES + ": " + cookieName + "|" + cookieValue);
  }

  /**
   * Clear all cookies.
   * 
   * @param driver
   */
  public static void clearAllCookies(WebDriver driver) {
    driver.manage().deleteAllCookies();
    logStep(CLEARED + COOKIES);
  }

  /**
   * Deletes the specified cookiesName.
   * 
   * @param driver
   * @param cookieName
   * @param cookieValue
   */
  public static void deleteCookie(WebDriver driver, String cookieName,
      String cookieValue) {
    driver.manage().deleteCookie(new Cookie(cookieName, cookieValue));
    logStep(CLEARED + COOKIES + ": " + cookieName);
  }

  /**
   * Returns the current url in the active window for the given driver.
   * 
   * @param driver
   * @return
   */
  public static String getCurrentUrl(WebDriver driver) {
    String currentUrl = driver.getCurrentUrl();
    logStep(CURRENT_URL + currentUrl);
    return currentUrl;
  }

  /**
   * Switches to window with the specified title when there are multiple
   * windows.
   * 
   * @param driver
   * @param windowTitle
   */
  public static void switchToWindowUsingTitle(WebDriver driver,
      String windowTitle) {
    Set<String> handlers = driver.getWindowHandles();
    if (driver.getWindowHandles().size() >= 1) {
      for (String handler : handlers) {
        driver.switchTo().window(handler);
        if (driver.getTitle().contains(windowTitle)) {
          break;
        }
      }
    }
  }

  /**
   * Switches to window with the specified locator when there are multiple
   * windows.
   * 
   * @param driver
   * @param windowTitle
   */
  public static void switchToWindowUsingLocator(WebDriver driver, By locator) {
    Set<String> handlers = driver.getWindowHandles();
    if (driver.getWindowHandles().size() >= 1) {
      for (String handler : handlers) {
        driver.switchTo().window(handler);
        if (isElementPresent(driver, locator)) {
          break;
        }
      }
    }
  }

  /**
   * Switches to window with the specified handle
   * 
   * @param driver
   * @param handle
   */
  public static void switchToWindowHandle(WebDriver driver, String handle) {
    if (!driver.getWindowHandle().equals(handle)) {
      driver.switchTo().window(handle);
    }
  }

  /**
   * Switches to iframe identified with the specified locator.
   * 
   * @param driver
   * @param handle
   */
  public static void switchToFrame(WebDriver driver, By locatorForFrame) {
    if (pollForElementAndCheckIfPresent(driver, locatorForFrame)) {
      driver.switchTo().frame(getElement(driver, locatorForFrame));
      logStep(SWITCHED_TO_IFRAME + locatorForFrame);
    } else {
      throw new ElementNotFoundException(locatorForFrame,
          "Iframe not found by:" + "\n", driver);
    }
  }

  /**
   * Switches to idefault content.Use this to get out of a iframe.
   * 
   * @param driver
   * @param handle
   */
  public static void switchToDefault(WebDriver driver) {
    driver.switchTo().defaultContent();
  }

  /**
   * Captures the screenshot of the current page when the SCREENSHOT system
   * property is set to 'on'. eg:SCREENSHOT=ON in bat and your pom.xml should
   * have the SCREENSHOT property defined.
   * <p>
   * This method is designed to work both on RemoteWebDriver and local driver.
   * </p>
   * <b>USAGE:</b>
   * <p>
   * To capture the screenshot explicitly for a step and show it in logs please
   * use as below. <b>If you are using SoftAssertor screenshots are captured
   * automatically.</b> For any assertions failures and automatically shown in
   * the report you do not have to write the below step.
   * 
   * <code><br><br>logStep(capturScreenShot(driver));</code>
   * </p>
   * 
   * @param driver
   */
  /*public static String captureScreenShot(WebDriver driver) {
    if ("on".equalsIgnoreCase(System.getProperty("SCREENSHOT"))) {

      try {
        // If the driver is RemoteWebDriver then augment it to enable screen
        // shots on it.
        if (driver.getClass().getName().contains("RemoteWebDriver")) {
          driver = new Augmenter().augment(driver);
        }
        final DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        final DateFormat dfFolder = new SimpleDateFormat(DATE_FORMAT);
        File scrFile = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);

        String fileName = SCREEN_SHOT_DIR + dfFolder.format(new Date())
            + FORWARD_SLASH + BROWSER + FORWARD_SLASH + df.format(new Date())
            + SCREEN_SHOT_EXTENSION;
        FileUtils.copyFile(scrFile, new File(fileName));
        String link = "  <b><a href='" + REPORT_PATH + fileName
            + "' onclick=\"window.open('" + REPORT_PATH + fileName
            + "','popup','"
            + "width=800,height=1500,toolbar=no,directories=no,location=no,"
            + "menubar=no,status=no,left=0,top=0'); return false\">"
            + "screenshot" + "</a></b>  <a href='" + driver.getCurrentUrl()
            + "' target='_blank' >" + "    url " + "</a>";
        return link;
      } catch (Exception e) {
        logFailureStep("Could not capture screenshot due to:" + e);
      }
    }
    return "";
  }
*/
  /**
   * Returns the size of the current browser used by the test.
   * @param driver
   * @return browser size as {@link String} eg:"W:1600 H:900"
   */
  public static String getBrowserSize(WebDriver driver) {
    return "W: " + driver.manage().window().getSize().getWidth() + " H: "
        + driver.manage().window().getSize().getHeight();
  }

  /**
   * <p>
   * Polls to a max of 30 seconds for a element and clicks it. Use
   * waitAndClick(final WebDriver driver, final By locator, String
   * failureMsgToShowIfEmntNotFound) to provide clean msg while elemnt to be
   * clicked is not found.
   * </p>
   * 
   * @param driver
   * @param locator
   *          to locate the element.
   */
  public static void waitAndClick(final WebDriver driver, final By locator)
      throws RuntimeException {
    WebElement element = getElement(driver, locator);
    String text = element.getText();
    String elementName = (text.trim().length() == 0 && element
        .getAttribute("value") != null) ? element.getAttribute("value") : text;
    element.click();
    logStep(CLICK_EVENT + SEPARATOR + elementName);
  }

  /**
   * Polls for a element and clicks it.
   * If the element is not available, the failure msg is shown along with the screenshot.
   * @param driver
   * @param locator
   *          to locate the element.
   * @param failureMsgToShowIfEmntNotFound
   */
  public static void waitAndClick(final WebDriver driver, final By locator,
      String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement element = getElement(driver, locator);
      String text = element.getText();
      String elementName = (text.trim().length() == 0 && element
          .getAttribute("value") != null) ? element.getAttribute("value")
          : text;
      element.click();
      logStep(CLICK_EVENT + SEPARATOR + elementName);
    } catch (Exception e) {
      String msg = e.getMessage();
      if(msg!=null && msg.contains("Exception:org.openqa.selenium.TimeoutException")) {
        msg = msg.substring(0, msg
            .indexOf("Exception:org.openqa.selenium.TimeoutException:"));
      }
      logRedStep(failureMsgToShowIfEmntNotFound);
      logRedStep("Could not click " + locator);
      throw new ElementNotFoundException(locator,
          failureMsgToShowIfEmntNotFound + "\n" + msg);
    }
  }

  /**
   * Polls for the parent element/locatorForParentElement,
   *  then locates the child/locatorForElement and clicks the child.
   * 
   * @param driver
   * @param locator
   *          to locate the element.
   * @param failureMsgToShowIfEmntNotFound
   */
  public static void waitAndClickElementWithinParent(final WebDriver driver, final By locatorForParentElement,
      final By locatorForElement, String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement element = getElementFromParent(driver, locatorForParentElement, locatorForElement);
      String text = element.getText();
      String elementName = (text.trim().length() == 0 && element
          .getAttribute("value") != null) ? element.getAttribute("value")
          : text;
      element.click();
      logStep(CLICK_EVENT + SEPARATOR + elementName);
    } catch (Exception e) {
      String msg = e.getMessage();
      if(msg!=null && msg.contains("Exception:org.openqa.selenium.TimeoutException")) {
        msg = msg.substring(0, msg
            .indexOf("Exception:org.openqa.selenium.TimeoutException:"));
      }
      logRedStep(failureMsgToShowIfEmntNotFound);
      logRedStep("Could not click " + locatorForElement);
      throw new ElementNotFoundException(locatorForElement,
          failureMsgToShowIfEmntNotFound + "\n" + msg);
    }
  }

  /**
   * Polls for the parent element/locatorForParentElement,
   *  then locates the child/locatorForElement and mouse overs the child.
   * 
   * @param driver
   * @param locator
   *          to locate the element.
   * @param failureMsgToShowIfEmntNotFound
   */
  public static void mouseOverElementWithinParent(final WebDriver driver, final By locatorForParentElement,
      final By locatorForChildToHover, final By locatorToFindAfterHovering,
      String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement hoverOn = getElementFromParent(driver, locatorForParentElement, locatorForChildToHover);
      String text = hoverOn.getText();
      String tagName = hoverOn.getTagName();
      Actions actions = new Actions(driver);
      WebElement menuHoverLink = hoverOn;
      actions.moveToElement(menuHoverLink);
      actions.perform();
      WaitForUtility.waitForElementToBeVisible(driver,
          locatorToFindAfterHovering);
      logStep(HOVERED + tagName + SEPARATOR + text + FOUND + locatorToFindAfterHovering);
    } catch (Exception e) {
      logRedStep("Could not hover over " + locatorForChildToHover + " contained in " + locatorForParentElement);
      throw new ElementNotFoundException(locatorForChildToHover,
          failureMsgToShowIfEmntNotFound + "\n" + failureMsgToShowIfEmntNotFound, driver);
    }
    
  }

  /**
   * Polls for the parent element/locatorForParentElement,
   *  then locates the child/locatorForElement and mouse overs the child.
   * 
   * @param driver
   * @param locator
   *          to locate the element.
   * @param failureMsgToShowIfEmntNotFound
   */
  public static void mouseOverElementWithinParent(final WebDriver driver, final By locatorForParentElement,
      final By locatorForChildToHover, String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement hoverOn = getElementFromParent(driver, locatorForParentElement, locatorForChildToHover);
      String text = hoverOn.getText();
      String tagName = hoverOn.getTagName();
      Actions actions = new Actions(driver);
      WebElement menuHoverLink = hoverOn;
      actions.moveToElement(menuHoverLink);
      actions.perform();
      logStep(HOVERED + tagName + SEPARATOR + text);
    } catch (Exception e) {
      logRedStep("Could not hover over " + locatorForChildToHover + " contained in " + locatorForParentElement);
      throw new ElementNotFoundException(locatorForChildToHover,
          failureMsgToShowIfEmntNotFound + "\n" + failureMsgToShowIfEmntNotFound, driver);
    }
    
  }

  /**
   * Polls for a element and gets text from it.
   * 
   * @param driver
   * @param locator
   *          to locate the element.
   * @param failureMsgToShowIfEmntNotFound
   */
  public static String waitAndGetText(final WebDriver driver, final By locator,
      String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement element = getElement(driver, locator);
      return element.getText();
    } catch (Exception e) {
      String msg = e.getMessage();
      if(msg!=null && msg.contains("Exception:org.openqa.selenium.TimeoutException")) {
        msg = msg.substring(0, msg
            .indexOf("Exception:org.openqa.selenium.TimeoutException:"));
      }
      logRedStep(failureMsgToShowIfEmntNotFound);
      logRedStep("Could not getText from " + locator);
      throw new ElementNotFoundException(locator,
          failureMsgToShowIfEmntNotFound + "\n" + msg, driver);
    }
  }

  
  public static String waitAndGetTextFromElementWithinParent(final WebDriver driver, final By locatorForParentElement,
      final By locatorForChildElementWhichIsUnderParent, String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      WebElement element = getElementFromParent(driver, locatorForParentElement, locatorForChildElementWhichIsUnderParent);
      return element.getText();
    } catch (Exception e) {
      String msg = e.getMessage();
      if(msg!=null && msg.contains("Exception:org.openqa.selenium.TimeoutException")) {
        msg = msg.substring(0, msg
            .indexOf("Exception:org.openqa.selenium.TimeoutException:"));
      }
      logRedStep(failureMsgToShowIfEmntNotFound);
      logRedStep("Could not getText from " + locatorForChildElementWhichIsUnderParent + " within " + locatorForParentElement);
      throw new ElementNotFoundException(locatorForChildElementWhichIsUnderParent,
          failureMsgToShowIfEmntNotFound + "\n" + msg, driver);
    }
  }

 
  /**
   * Polls for a locator with attribute 'attribute' that
   * contains 'containingString' and clicks it.
   */
  public static void waitAndClickElementContainingAttributePartly(
      WebDriver driver, String attribute, String containingString, By locator)
      throws RuntimeException {
    WebElement element = getElementsContainingAttributePartly(driver,
        attribute, containingString, locator);
    String text = element.getText();
    String elementName = (text.trim().length() == 0) ? element
        .getAttribute("value") : text;
    element.click();
    logStep(CLICK_EVENT + SEPARATOR + elementName);
  }

  /**
   * Polls for a element and gets text from it.
   * 
   * @return String text
   * @param driver
   * @param locator
   *          to locate the element.
   */
  public static String waitAndGetText(final WebDriver driver, final By locator) {
    return getElement(driver, locator).getText();

  }

  /**
   * Gets text from the locator.
   * <p>
   * This does not poll for the element.Returns null if the element is not
   * present.
   * </p>
   * 
   * @return String text
   * @param driver
   * @param locator
   *          to locate the element.
   */
  public static String getText(final WebDriver driver, final By locator) {
    if (isElementPresent(driver, locator)) {
      return driver.findElement(locator).getText();
    }
    throw new ElementNotFoundException(locator, "Could not getText", driver);
  }

  /**
   * Use waitAndGetText(final WebDriver driver, final By locator) instead. Note
   * the 'G' instead of 'g' in the method name.
   */
  
  public static String waitAndgetText(final WebDriver driver, final By locator) {
    return getElement(driver, locator).getText();

  }

  /**
   * Polls to a max of 30 seconds to locate the image by the specified locator
   * and gets alt from it.
   * <p>
   * Note: This methods is intended for images that has 'alt' attribute in the
   * image tag. In some cases the image seen on UI may be defined in a anchor
   * tag and the mouse over text could be defined in 'title' attribute.
   * </p>
   * 
   * @param driver
   * @param imageLocator
   * @return String the alt text from the image.
   */
  public static String getImageAltText(final WebDriver driver,
      final By imageLocator) {
    return getElement(driver, imageLocator).getAttribute("alt");
  }

  /**
   * Polls to a max of 30 seconds to locate the image by the specified locator
   * and gets source of the background image associated with the element.
   * 
   * @param driver
   * @param imageLocator
   * @return String the background image source .
   */
  public static String getBackgroundImageSource(final WebDriver driver,
      final By imageLocator) {
    return getElement(driver, imageLocator).getCssValue("background-image");
  }

  /**
   * Polls to a max of 30 seconds to locate the element by the specified locator
   * and gets css value from the css property specified as cssToGetValueFrom
   * associated with the element.
   * 
   * @param driver
   * @param imageLocator
   * @param cssToGetValueFrom
   * @return String the background image source .
   */
  public static String getCssValue(final WebDriver driver, final By locator,
      String cssToGetValueFrom) {
    return getElement(driver, locator).getCssValue(cssToGetValueFrom);
  }

  /**
   * Gets the text seen on UI while performing a mouse over on a element.
   * <p>
   * Note: This method is written to get the 'title' attribute from anchor tags.
   * </p>
   * 
   * @param driver
   * @param elementText
   *          Text seen on the image button or link
   * @param locator
   * @return String mouseover text seen while hovering on image button or link
   */
  public static String getMouseOverText(final WebDriver driver,
      final String elementText, final By locator) {
    for (WebElement element : getAllElementsWithSameLocator(driver, locator)) {
      if (element.getText().contains(elementText)) {
        return element.getAttribute("title");
      }
    }
    throw new ElementNotFoundException(locator,
        "Could not find mouse over text using title.", driver);
  }

  /**
   * Polls to a max of 30 seconds for a element and gets alt from it.
   * 
   * @param driver
   * @param imageLocator
   * @return String the alt text from the image.
   */
  public static List<String> getImageAltTextFromAllElementsWithSameLocator(
      final WebDriver driver, final By imageLocator) {
    WaitForUtility.waitForElementToBeVisible(driver, imageLocator);
    List<WebElement> elements = driver.findElements(imageLocator);
    List<String> altValuesList = new ArrayList<String>();
    for (WebElement element : elements) {
      altValuesList.add(element.getAttribute("alt"));
    }
    return altValuesList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets alt from it.
   * 
   * @param driver
   * @param imageLocator
   * @return String the alt text from the image.
   */
  public static List<String> getImageSrcTextFromAllElementsWithSameLocator(
      final WebDriver driver, final By imageLocator) {
    WaitForUtility.waitForElementToBeVisible(driver, imageLocator);
    List<WebElement> elements = driver.findElements(imageLocator);
    List<String> srcValuesList = new ArrayList<String>();
    for (WebElement element : elements) {
      srcValuesList.add(element.getAttribute("src"));
    }
    return srcValuesList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets href value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static String getHrefFromLink(final WebDriver driver,
      final By linkLocator) {
    return getElement(driver, linkLocator).getAttribute("href");
  }

  /**
   * Polls to a max of 30 seconds for a element and gets href value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static List<String> getHrefFromAllElementsWithSameLocator(
      final WebDriver driver, final By linkLocator) {
    WaitForUtility.waitForElementToBeVisible(driver, linkLocator);
    List<WebElement> elements = driver.findElements(linkLocator);
    List<String> hrefValuesList = new ArrayList<String>();
    for (WebElement element : elements) {
      hrefValuesList.add(element.getAttribute("href"));
    }
    return hrefValuesList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets style value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static String getStyleFromElement(final WebDriver driver,
      final By linkLocator) {
    return getElement(driver, linkLocator).getAttribute("style");
  }

  /**
   * Polls to a max of 30 seconds for a element and gets style value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static List<String> getStyleFromAllElementsWithSameLocator(
      final WebDriver driver, final By linkLocator) {
    WaitForUtility.waitForElementToBeVisible(driver, linkLocator);
    List<WebElement> elements = driver.findElements(linkLocator);
    List<String> valuesList = new ArrayList<String>();
    for (WebElement element : elements) {
      valuesList.add(element.getAttribute("style"));
    }
    return valuesList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static String getValueFromElement(final WebDriver driver,
      final By linkLocator) {
    return getElement(driver, linkLocator).getAttribute("value");
  }

  /**
   * Polls to a max of 30 seconds for a element and gets value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String the href value from the link.
   */
  public static List<String> getValueFromAllElementsWithSameLocator(
      final WebDriver driver, final By linkLocator) {
    WaitForUtility.waitForElementToBeVisible(driver, linkLocator);
    List<WebElement> elements = driver.findElements(linkLocator);
    List<String> valuesList = new ArrayList<String>();
    for (WebElement element : elements) {
      valuesList.add(element.getAttribute("value"));
    }
    return valuesList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets src value from it.
   * 
   * @param driver
   * @param linkLocator
   * @return String src value from the image.
   */
  public static String getSrcFromImageTag(final WebDriver driver,
      final By imageLocator) {
    return getElement(driver, imageLocator).getAttribute("src");
  }

  /**
   * Clicks on a element and logs the event.
   * 
   * @param webElement
   */
  public static void click(final WebElement webElement) {
    String text = webElement.getText();
    String tagName = webElement.getTagName();
    if (tagName.contains("input")) {
      tagName = webElement.getAttribute("value");
    }
    try {
      webElement.click();
      logStep(CLICK_EVENT + tagName + SEPARATOR + text);
    } catch (ElementNotVisibleException e) {
      logFailureStep("ElementNotFoundException thrown at click" + e.toString());
    } catch (StaleElementReferenceException e) {
      logFailureStep("StaleElementReferenceException thrown at " + e.toString());
    }
  }

  /**
   * Clicks on a element logs the event and returns the element back.
   * 
   * @param webElement
   * @return
   */
  
  public static WebElement clickAndReturnElement(final WebElement webElement) {
    String text = webElement.getText();
    String tagName = webElement.getTagName();
    if (tagName.contains("input")) {
      tagName = webElement.getAttribute("value");
    }
    try {
      webElement.click();
      logStep(CLICK_EVENT + tagName + SEPARATOR + text);
      return webElement;
    } catch (ElementNotVisibleException e) {
      logFailureStep("ElementNotVisibleException - clickAndReturnElement"
          + e.toString());
      return null;
    } catch (StaleElementReferenceException e) {
      logFailureStep("StaleElementReferenceException - clickAndReturnElement"
          + e.toString());
      return null;
    }
  }

  /**
   * Types the 'textToBeTyped' in the specified webElement and logs the event.
   * 
   * @param webElement
   * @param textToBeTyped
   */
  public static void sendKeys(final WebElement webElement,
      final String textToBeTyped) {
    webElement.sendKeys(textToBeTyped);
    logStep(TYPED + textToBeTyped);
  }

  /**
   * Types the 'textToBeTyped' in the specified alert and logs the event.
   * 
   * @param alert
   * @param textToBeTyped
   */
  public static void sendKeys(final Alert alert, final String textToBeTyped) {
    alert.sendKeys(textToBeTyped);
    logStep(TYPED + textToBeTyped);
  }

  /**
   * Types the 'textToBeTyped' in the specified webElement and logs the event.
   * 
   * @param webElement
   * @param textToBeTyped
   */
  public static void pollForTextBoxAndSendKeys(final WebDriver driver,
      final By locator, final String textToBeTyped) {
    getElement(driver, locator).sendKeys(textToBeTyped);
    logStep(TYPED + textToBeTyped);
  }

  /**
   * Clears the value in webElement and logs the event.
   * 
   * @param webElement
   */
  public static void clear(final WebElement webElement) {
    String text = webElement.getText();
    webElement.clear();
    logStep(CLEARED + SEPARATOR + text);
  }

  /**
   * Clears the value in locator and logs the event.
   * 
   * @param webElement
   */
  public static void clear(final WebDriver driver, final By locator) {
    WebElement element = getElement(driver, locator);
    logStep("Value in field before clearing:" + element.getAttribute("value"));
    element.clear();
    logStep(CLEARED + SEPARATOR + element.getText());
    logStep("Value in field after clearing:" + element.getAttribute("value"));
  }

  /**
   * Clears the value in webElement and logs the event.
   * 
   * @param webElement
   */
  public static void clearFieldAndSendText(final WebDriver driver,
      final By locator, final String textToBeTyped) {
    WebElement element = getElement(driver, locator);
    element.clear();
    element.click();
    pollForTextBoxAndSendKeys(driver, locator, textToBeTyped);
  }

  /**
   * Submits the form in webElement and logs the event.
   * 
   * @param webElement
   */
  public static void submit(final WebElement webElement) {
    String text = webElement.getText();
    webElement.submit();
    logStep(SUBMITTED + SEPARATOR + text);
  }

  /**
   * Hover mouse over the "By" locator identified by 'hoverOn' and polls for
   * 'locatorToFindOnPageAfterHovering' and logs the event.
   * 
   * @param hoverOn
   * @param locatorToFindOnPageAfterHovering
   * @param driver
   */
  public static void mouseOver(final By hoverOn,
      final By locatorToFindOnPageAfterHovering, final WebDriver driver) {
    String text = getElement(driver, hoverOn).getText();
    String tagName = getElement(driver, hoverOn).getTagName();
    Actions actions = new Actions(driver);
    WebElement menuHoverLink = driver.findElement(hoverOn);
    actions.moveToElement(menuHoverLink);
    actions.perform();
    //MouseActionUtility.doMouseActionUsingJS("mouseover", hoverOn, driver);
    WaitForUtility.waitForElementToBeVisible(driver,
        locatorToFindOnPageAfterHovering);
    logStep(HOVERED + "<" + tagName + ">" + SEPARATOR + text + FOUND
        + locatorToFindOnPageAfterHovering);
  }

  /**
   * Hover mouse over the WebElement identified by 'hoverOnElement' and polls
   * for 'locatorToFindOnPageAfterHovering' and logs the event.
   * 
   * @param hoverOnElement
   * @param locatorToFindOnPageAfterHovering
   * @param driver
   */
  public static void mouseOver(WebElement hoverOnElement,
      final By locatorToFindOnPageAfterHovering, final WebDriver driver) {
    String text = hoverOnElement.getText();
    String tagName = hoverOnElement.getTagName();

    ActionUtility
        .doMouseActionUsingJS("mouseover", hoverOnElement, driver);
    WaitForUtility.waitForElementToBeVisible(driver,
        locatorToFindOnPageAfterHovering);
    logStep(HOVERED + "<" + tagName + ">" + SEPARATOR + text + FOUND
        + locatorToFindOnPageAfterHovering);
  }

  /**
   * Selects option from drop down if the select box uses select tag and options
   * uses option tags like shown. <select> <option> optionToSelect </option>
   * </select>
   * 
   * @param driver
   * @param optionToSelect
   */
  public static void selectOptionFromDropDown(WebDriver driver,
      By selectLocator, String optionToSelect) {
    WebElement select = getElement(driver, selectLocator);
    List<WebElement> options = select.findElements(By.tagName("option"));
    for (WebElement option : options) {
      if (optionToSelect.equals(option.getText())) {
        option.click();
        logBoldStep("Selected " + option.getText() + " from " + select.getAttribute("name"));
        break;
      }
    }
  }

  /**
   * Gets all the options from the specified select box as {@link List} of
   * Strings .
   */
  public static List<String> getAllOptionsFromDropDown(WebDriver driver,
      By selectBoxLocator) {
    WebElement select = getElement(driver, selectBoxLocator);
    List<WebElement> options = select.findElements(By.tagName("option"));
    List<String> optionTexts = new ArrayList<String>();
    for (WebElement option : options) {
      optionTexts.add(option.getText());
    }
    return optionTexts;
  }

  /**
   * Gets all the options from the specified select box as {@link List} of
   * Strings .
   */
  public static List<String> getAllOptionsFromDropDownLocatedByName(
      WebDriver driver, String selectBoxName) {
    List<WebElement> selectOptions = getAllElementsWithSameLocator(driver, By
        .cssSelector("select[name=\"" + selectBoxName.trim() + "\"]>option"));
    List<String> allOptions = new ArrayList<String>();
    for (WebElement selectOption : selectOptions) {
      allOptions.add(selectOption.getText());
    }
    return allOptions;
  }

  /**
   * Selects option from drop down if the select box uses select tag and options
   * uses option tags and has name associated with the select box. <select>
   * <option> optionToSelect </option> </select>
   * 
   * @param driver
   * @param optionToSelect
   * @throws RuntimeException
   *           if the optionToSelect is not available.
   */
  public static void selectOptionByLocatingDropDownWithName(WebDriver driver,
      String selectBoxName, String optionToSelect) throws RuntimeException {
    List<WebElement> selectOptions = getAllElementsWithSameLocator(driver, By
        .cssSelector("select[name=\"" + selectBoxName.trim() + "\"]>option"));
    String availableOptions = "";
    for (WebElement selectOption : selectOptions) {
      availableOptions = selectOption.getText() + ", " + availableOptions;
      if (selectOption.getText().trim().equals(optionToSelect.trim())) {
        ActionUtility.logStep("Selecting option:" + optionToSelect);
        selectOption.click();
        return;
      }
    }
    throw new RuntimeException("Options: " + optionToSelect
        + " is not available, Available options are:" + availableOptions);
  }

  /**
   * Get all select box labels and their respective options in the current page,
   * as map.
   * 
   * <p>
   * <b>Note:</b> The select boxes should have name attribute for this method to
   * work. This is the case with most of the select boxes.
   * </p>
   * 
   * @return {@code Map<String, List<String>>}
   */
  public static Map<String, List<String>> getSelectBoxAndOptionsOnPageAsMap(
      WebDriver driver) {
    List<WebElement> allSelectBoxes = getAllElementsWithSameLocator(driver, By
        .cssSelector("select"));
    Map<String, List<String>> mapOfSelectBoxAndValues = new LinkedHashMap<String, List<String>>();
    for (WebElement selectBox : allSelectBoxes) {
      String selectBoxLabel = selectBox.getAttribute("name");
      List<String> options = getTextsFromAllElementsWithSameLocator(driver, By
          .cssSelector("select[name=\"" + selectBoxLabel + "\"]>option"));
      mapOfSelectBoxAndValues.put(selectBoxLabel, options);
    }
    return mapOfSelectBoxAndValues;
  }

  /**
   * Returns selection option from drop down if the select box uses select tag
   * and options uses option tags like shown. <select> <option> optionToSelect
   * </option> </select>
   * 
   * @param driver
   * @param optionToSelect
   */
  public static WebElement getSelectedOptionFromDropDown(WebDriver driver,
      By selectLocator) {
    WebElement select = getElement(driver, selectLocator);
    List<WebElement> options = select.findElements(By.tagName("option"));
    for (WebElement option : options) {
      if (option.isSelected()) {
        return option;
      }
    }
    return options.get(0);
  }

  /**
   * Gets texts for all the elements sharing the same locator and return them in
   * a single string.
   * 
   * @return text of all elements sharing a locator as a String
   */
  public static String getTextForAllElementsWithSameLocator(WebDriver driver,
      By locator) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    List<WebElement> elements = driver.findElements(locator);
    StringBuffer textSB = new StringBuffer();
    for (WebElement element : elements) {
      textSB.append(element.getText());
    }
    return textSB.toString();
  }

  /**
   * Gets texts for all the elements sharing the same locator and return them in
   * a {@link List}.
   * 
   * @return {@link List} of all elements sharing a locator
   */
  public static List<String> getTextsFromAllElementsWithSameLocator(
      WebDriver driver, By locator) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    List<WebElement> elements = driver.findElements(locator);
    List<String> textList = new ArrayList<String>();
    for (WebElement element : elements) {
      textList.add(element.getText());
    }
    return textList;
  }

  /**
   * Waits for the locator and gets all the elements sharing same locator.
   * 
   * @return {@link List} of all elements sharing a locator
   */
  public static List<WebElement> getAllElementsWithSameLocator(
      WebDriver driver, By locator) throws RuntimeException {
    try {
      WaitForUtility.waitForElementToBeVisible(driver, locator);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return driver.findElements(locator);
  }

  public static List<WebElement> getAllElementsWithSameLocator(
      WebDriver driver, By locator, String failureMsgToShowIfEmntNotFound) throws RuntimeException {
    try {
      return getAllElementsWithSameLocator(driver, locator);
    } catch (Exception e) {
      logRedStep("Could not click " + locator);
      String msg = e.getMessage();
      if(msg!=null && msg.contains("Exception:org.openqa.selenium.TimeoutException")) {
        msg = msg.substring(0, msg
            .indexOf("Exception:org.openqa.selenium.TimeoutException:"));
      }

      throw new ElementNotFoundException(locator,
          failureMsgToShowIfEmntNotFound + "\n" + msg);
    }
  }
  /**
   * Locates a element or several elements by locator(eg:xpath-> //input or css
   * -> input) and iterates through all elements then gets the attribute and
   * checks if the containingString is a part of the attribute. If so returns
   * the element.
   * 
   * <b>USAGE: getElementsContainingAttributePartly(driver, "class", "qnty",
   * LOCATOR)</b>
   */
  public static WebElement getElementsContainingAttributePartly(
      WebDriver driver, String attribute, String containingString, By locator)
      throws RuntimeException {
    List<WebElement> elementsContainingAttribute = getAllElementsWithSameLocator(
        driver, locator);
    for (WebElement webElement : elementsContainingAttribute) {
      String value = webElement.getAttribute(attribute);
      if ((value != null) && (value.length() != 0)
          && (value.contains(containingString))) {
        return webElement;
      }

      //ActionUtility.logRedStep(ActionUtility.captureScreenShot(driver));
    }
    throw new RuntimeException("None of the elements located by '" + locator
        + "' contained the '" + attribute
        + "' attribute having value containing '" + containingString + "'.");
  }

  /**
   * Locates a element or several elements by locator(eg:xpath-> //input or css
   * -> input) and iterates through all elements then gets the attribute and
   * checks if the containingString is a part of the attribute. If so returns
   * true.
   * 
   * <b>USAGE: isElementsContainingAttributePartlyPresent(driver, "class",
   * "qnty", LOCATOR)</b>
   */
  public static boolean isElementContainingAttributePartlyPresent(
      WebDriver driver, String attribute, String containingString, By locator)
      throws RuntimeException {
    List<WebElement> elementsContainingAttribute = getAllElementsWithSameLocator(
        driver, locator);
    for (WebElement webElement : elementsContainingAttribute) {
      String value = webElement.getAttribute(attribute);
      if ((value != null) && (value.length() != 0)
          && (value.contains(containingString))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the number of elements that share the same locator.
   * 
   * @return int # of all elements sharing a locator
   */
  public static int getCountForElementsWithSameLocator(WebDriver driver,
      By locator) {
    try {
      WaitForUtility.waitForElementToBeVisible(driver, locator);
      return driver.findElements(locator).size();
    } catch (Exception e) {
      return 0;
    }

  }

  /**
   * Polls for the element for a max of 30 seconds and returns the elements if
   * it is found.
   * 
   * @return {@link List} of all elements sharing a locator
   */
  public static WebElement getElement(WebDriver driver, By locator)
      throws RuntimeException {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    return driver.findElement(locator);
  }

  /**
   * Polls for the element for a max of 30 seconds and returns the elements if
   * it is found.
   * 
   * @return {@link List} of all elements sharing a locator
   */
  public static WebElement getElementFromParent(WebDriver driver, By locatorForParentElement,
      By locatorForChildElementWhichIsUnderParent)
      throws RuntimeException {
    WaitForUtility.waitForElementUnderParentToBeVisible(driver, locatorForParentElement, locatorForChildElementWhichIsUnderParent);
    return driver.findElement(locatorForParentElement).findElement(locatorForChildElementWhichIsUnderParent);
  }

  /**
   * Logs the message to be seen from testng report.
   * 
   * @param message
   */
  public static void logStep(final String message) {
    Reporter.log(message);
  }

  /**
   * Logs the <b>message in bold</b> and is to be seen from testng report.
   * 
   * @param message
   */
  public static void logBoldStep(final String message) {
    Reporter.log("<b>" + message + "</b>");
  }

  
  public static void logFailureStep(final String message) {
    Reporter.log("<b>" + message + "</b>");
  }

  /**
   * Logs the message in <b style="color:red;">bold red color</b> to be seen
   * from testng report.
   * 
   * @param message
   */
  public static void logRedStep(final String message) {
    Reporter.log("<b style=\"color:red;\">" + message + "</b>");
  }

  /**
   * This method does not work anymore since, js is blocked in modern browsers
   * and it may not work for all browsers.
   * <p>
   * There is a bug logged in webdriver to build this in webdriver code base.
   * {@link http://code.google.com/p/selenium/issues/detail?id=174}
   * </p>
   * 
   * <b>Use resizeBrowserAndRefresh or resizeBrowser(driver, int, int)
   * instead.</b>
   * 
   * @param driver
   * @param windowWidth
   * @param windowHeight
   */
 
  public static void resizeBrowser(WebDriver driver, String windowWidth,
      String windowHeight) {
    logRedStep("ActionUtility.resizeBrowser(WebDriver driver, String windowWidth, String windowHeight)" +
    		" is a deprecated.Please use resizeBrowser(WebDriver driver, int windowWidth, int windowHeight)");
    ((JavascriptExecutor) driver).executeScript("window.resizeTo("
        + windowWidth + "," + windowHeight + ");");
  }

  /**
   * Resizes the browser to the size specified and refreshes the page.
   * 
   * <p>
   * Use in cases where a refresh is needed after resizing the browser for the
   * UI to adjust.
   * <p>
   * 
   * @param driver
   * @param int windowWidth
   * @param int windowHeight
   */
  public static void resizeBrowserAndRefresh(WebDriver driver, int windowWidth,
      int windowHeight) {
    logStep("<b>Requested Browser Size:</b> W:" + windowWidth + " H:"
        + windowHeight);
    driver.manage().window().setPosition(new Point(0, 0));
    driver.manage().window().setSize(new Dimension(windowWidth, windowHeight));
    logStep("<b>Actual Browser Size:</b> " + getBrowserSize(driver));
    driver.navigate().refresh();
    logStep("Refreshed page");
  }

  /**
   * Resizes the browser to test on various resolutions.
   * <p>
   * If you need the app to refresh after resizing, use {@code
   * resizeBrowserAndRefresh(driver, int, int)}
   * </p>
   * 
   * @param driver
   * @param int windowWidth
   * @param int windowHeight
   */
  public static void resizeBrowser(WebDriver driver, int windowWidth,
      int windowHeight) {
    logStep("<b>Requested Browser Size:</b> W:" + windowWidth + " H:"
        + windowHeight);
    driver.manage().window().setPosition(new Point(0, 0));
    driver.manage().window().setSize(new Dimension(windowWidth, windowHeight));
    logStep("<b>Actual Browser Size:</b> " + getBrowserSize(driver));
  }

  public static WebElement clickAndVerifyElementWhenReady(final By locator,
      final By locatorToVerify, final WebDriver driver) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    return wait.until(new Function<WebDriver, WebElement>() {
      public WebElement apply(WebDriver driver) {
        WebElement element = driver.findElement(locator);
        element.click();
        if (element.isDisplayed()) {
          try {
            WaitForUtility.waitForElementToBeVisible(driver, locatorToVerify);
          } catch (Exception e) {
            return null;
          }
          return element;
        }
        return null;
      }
    });
  }

  /**
   * Returns true if element is present. Note:This does not poll. Use this if
   * your element should be present immediately.
   * 
   * @param driver
   * @param locator
   *          to find the element
   * @return true if element is present.
   */
  public static boolean isElementPresent(WebDriver driver, By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Poll for the maximum timeout of 30 secs to find the element. Returns true
   * if the element is found any time on or before timeout.
   * 
   * @param driver
   * @param locator
   *          to find the element
   * @return true if element is present.
   */
  public static boolean pollForElementAndCheckIfPresent(WebDriver driver,
      By locator) {
    try {
      pollForElementToBeVisible(driver, locator);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 
   * Pool for an element and refresh after the buffer time if element is not
   * found.
   * 
   * @param driver
   * @param locator
   * @param noOfSecondsBeforeRefresh
   * @param maxWaitTime
   *          to wait
   * @return
   */
  public static boolean pollForElementByRefreshing(WebDriver driver, By locator,
      int noOfSecondsBeforeRefresh, int maxWaitTime) {
    try {
      while ((!pollForElementAndCheckIfPresent(driver, locator,
          noOfSecondsBeforeRefresh))
          && (maxWaitTime > 0)) {
        maxWaitTime = maxWaitTime - noOfSecondsBeforeRefresh;
        driver.navigate().refresh();
        pollForElementAndCheckIfPresent(driver, locator,
            noOfSecondsBeforeRefresh);
      }
      if (maxWaitTime > 0)
        return true;
      else
        return false;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Returns true if element is present.Polls for the specified timeout.
   * 
   * @param driver
   *          {@link WebDriver}
   * @param locator
   *          {@link By}
   * @param timeOutInSeconds
   *          {@link Long}
   * @return
   */
  public static boolean pollForElementAndCheckIfPresent(WebDriver driver,
      By locator, long timeOutInSeconds) {
    try {
      pollForElementToBeVisible(driver, locator, timeOutInSeconds);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Returns true if element is disappears within the specified timeout.
   * 
   * @param driver
   *          {@link WebDriver}
   * @param locator
   *          {@link By}
   * @param timeOutInSeconds
   *          {@link Long}
   * @return
   */
  public static boolean pollForElementToDisappear(WebDriver driver, By locator,
      long timeOutInSeconds) {
    try {
      WaitForUtility.waitUntilElementDisappears(driver, locator,
          timeOutInSeconds);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Waits for the element to be visible until a timeout of 30 secs.
   * 
   * @param driver
   * @param locator
   */
  private static void pollForElementToBeVisible(final WebDriver driver,
      final By locator) throws RuntimeException {
    Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    try {
      wait.until(new ExpectedCondition<WebElement>() {
        public WebElement apply(WebDriver driver) {
          // driver.switchTo().defaultContent();
          WebElement element = driver.findElement(locator);
          if (element.isDisplayed()) {
            return element;
          }
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("Exception while waiting for " + locator
          + ". Exception:" + e + " on " + driver.getCurrentUrl());
    }
  }

  /**
   * Polls for max timeout for element to be selected;
   */
  private static void pollForElementToBeSelected(final WebDriver driver,
      final By locator, final long pollMax) throws RuntimeException {
    Wait<WebDriver> wait = new WebDriverWait(driver, pollMax);
    try {
      wait.until(new ExpectedCondition<WebElement>() {
        public WebElement apply(WebDriver driver) {
          // driver.switchTo().defaultContent();
          WebElement element = driver.findElement(locator);
          if (element.isSelected()) {
            return element;
          }
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("Exception while waiting for " + locator
          + ". Exception:" + e + " on " + driver.getCurrentUrl());
    }
  }

  /**
   * Waits for the element to be visible until the specified timeout.
   * 
   * @param driver
   *          {@link WebDriver}
   * @param locator
   *          {@link By}
   * @param timeOut
   *          long
   */
  private static void pollForElementToBeVisible(final WebDriver driver,
      final By locator, long timeOut) throws RuntimeException {
    Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
    try {
      wait.until(new ExpectedCondition<WebElement>() {
        public WebElement apply(WebDriver driver) {
          // driver.switchTo().defaultContent();
          WebElement element = driver.findElement(locator);
          if (element.isDisplayed()) {
            return element;
          }
          return null;
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("Exception while waiting for " + locator
          + ". Exception:" + e);
    }
  }

  /**
   * Enters text on a prompt box and accepts it.
   * 
   * @param driver
   * @param textToBeTyped
   */
  public static void enterTextOnPromptAndAccept(WebDriver driver,
      String textToBeTyped) {
    Alert prompt = driver.switchTo().alert();
    logStep("Prompt Text:" + prompt.getText());
    sendKeys(prompt, textToBeTyped);
    prompt.accept();
  }

  /**
   * Accepts/Dismisses the confirmation alert.
   * 
   * @param driver
   * @param boolean accepts the alert if true and dismisses the alert if false
   */
  public static void acceptOnConfirmationBox(WebDriver driver, boolean accept) {
    Alert confirm = driver.switchTo().alert();
    logStep(confirm.getText());
    if (accept) {
      confirm.accept();
      logStep("Accepted alert.");
    } else {
      confirm.dismiss();
      logStep("Dismissed alert.");
    }
  }

  /**
   * Scrolls the page to the specifed pixel position in xAxis and yAxis.
   * <p>
   * <b>USAGE:</b><br>
   * 1.To scroll down to a position at pixel 1000 : {@code scrollPage(driver, 0,
   * 1000)}<br>
   * 2.To scroll right to a position at pixel 500 : {@code scrollPage(driver,
   * 500, 0)}<br>
   * 3.To scroll right to a position at pixel 500 and scroll down to a position
   * at pixel 2500 : {@code scrollPage(driver, 500, 2500)}
   * </p>
   * 
   * @param driver
   * @param xAxis
   *          String
   * @param yAxis
   *          String
   */
  public static void scrollPage(WebDriver driver, String xAxis, String yAxis) {
    ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + xAxis
        + "," + yAxis + ")");
    logStep("<b>Scrolled the page by </b> X:" + xAxis + " Y:" + yAxis);
  }

  /**
   * Scroll back to top of the page
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  public static Long scrollTop(WebDriver driver) {
    return (Long) ((JavascriptExecutor) driver)
        .executeScript("return document.documentElement.scrollTop");
  }

  /**
   * Scroll to bottom of the page
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  public static void scrollToBottom(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0,document.body.scrollHeight);");
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0,document.body.scrollHeight);");
    logStep("<b>Scrolled to the bottom of the page.</b>");
  }

  /**
   * Scroll back to bottom of the page
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  public static void clickBrowserBack(WebDriver driver) {
    driver.navigate().back();
    logStep("<b>Clicked browser's back button.</b>");
  }

  /**
   * Click scrollbar like slider for product configurations
   * 
   * @author haiyang
   * 
   * @param driver
   * @param width
   * @param sliderBarMemory
   */
  public static void clickScrollBar(WebDriver driver, int width,
      WebElement sliderBarMemory) {
    // "- 10 , 5" is offset args to make sure the mouse point is on the
    // sliderBar.
    ((HasInputDevices) driver).getMouse().mouseMove(
        ((Locatable) sliderBarMemory).getCoordinates(), width - 10, 5);
    ((HasInputDevices) driver).getMouse().click(null);
  }

  /**
   * Maximizes the window to the available screen width.
   * 
   * @param driver
   */
  public static void maximizeWindow(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("if (window.screen){window.moveTo(0, 0);"
            + "window.resizeTo(window.screen.availWidth,window.screen.availHeight);}");
  }

  /**
   * Method to check if element is rendered TODO(Sumathi): Remove this method.
   */
  @Deprecated
  public static boolean isElementRendered(final WebDriver driver,
      final By locator, String linkName) {
    try {

      Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
      wait.until(new ExpectedCondition<WebElement>() {
        public WebElement apply(WebDriver driver) {
          WebElement element = driver.findElement(locator);
          return element.isDisplayed() ? element : null;
        }
      });
      return true;
    } catch (Exception e) {
      logFailureStep(driver.getCurrentUrl() + linkName
          + " is not rendered properly.Please take a look - the new one");
      return false;
    }
  }

  /**
   * Method to validate and click the leftnav links in DCP and return h1 tag
   * TODO(Sumathi): Remove this method.
   */
  @Deprecated
  public static WebElement findXPathIsValid(String id, WebDriver driver) {

    try {
      WebElement element = null;
      element = driver.findElement(By.xpath(id));
      if (element != null) {
        element.click();
        return element;
      }

    } catch (Exception e) {
      logFailureStep("Verifyh1Text" + e.toString());
      return null;
    }

    return null;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets the attribute from the
   * parameter
   * 
   * @param driver
   * @param locator
   * @param attribute
   * @return List of values from the attribute
   */
  public static List<String> getAttributeFromAllElementsWithSameLocator(
      final WebDriver driver, final By locator, String attribute) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    List<WebElement> elements = driver.findElements(locator);
    List<String> attributeList = new ArrayList<String>();
    for (WebElement element : elements) {
      attributeList.add(element.getAttribute(attribute));
    }
    return attributeList;
  }

  /**
   * Polls to a max of 30 seconds for a element and gets the attribute from the
   * parameter
   * 
   * @param driver
   * @param locator
   * @param attribute
   * @return value of attribute
   */
  public static String getAttributeFromElement(final WebDriver driver,
      final By locator, String attribute) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    WebElement element = driver.findElement(locator);
    return element.getAttribute(attribute);
  }

  /**
   * Polls to a max of 30 seconds for a element and gets the attribute from the
   * parameter
   * 
   * @param driver
   * @param locator
   * @param attribute
   * @return value of attribute
   */
  public static String getAttributeFromElementWithinParent(final WebDriver driver, final By locatorForParent,
      final By locatorForChildElementWhichIsUnderParent, String attribute) {
    return getElementFromParent(driver, locatorForParent, locatorForChildElementWhichIsUnderParent).getAttribute(attribute);
  }

  /**
   * Returns the X location of the element.
   * 
   * @param driver
   * @param locator
   * @return
   */
  public static int getElementPositionX(WebDriver driver, By locator) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    WebElement element = driver.findElement(locator);
    return element.getLocation().getX();
  }

  /**
   * Returns the Y position of the element.
   * 
   * @param driver
   * @param locator
   * @return
   */
  public static int getElementPositionY(WebDriver driver, By locator) {
    WaitForUtility.waitForElementToBeVisible(driver, locator);
    WebElement element = driver.findElement(locator);
    return element.getLocation().getY();
  }

  /**
   * return screen resolution width
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  public static Long getScreenResolutionWidth(WebDriver driver) {
    return (Long) ((JavascriptExecutor) driver)
        .executeScript("return screen.width");
  }

  /**
   * USE getScreenResolutionHeight instead of this.This method has a typo.
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  @Deprecated
  public static Long getScreenResolutionHeifht(WebDriver driver) {
    return (Long) ((JavascriptExecutor) driver)
        .executeScript("return screen.height");
  }

  /**
   * return screen resolution height
   * 
   * @param driver
   * @param xAxis
   * @param yAxis
   * @return
   */
  public static Long getScreenResolutionHeight(WebDriver driver) {
    return (Long) ((JavascriptExecutor) driver)
        .executeScript("return screen.height");
  }

  public static void highlightElement(WebDriver driver, By locator) {
    try {
      if (pollForElementAndCheckIfPresent(driver, locator)) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
      }
    } catch (Exception e) {
      // This is a nice to have feature only hence ignore exception if there is
      // any since,
      // the element will just not be highlighted. There could be exceptions if
      // the browser
      // js is not permitting JavascriptExecutor from scripts.
    }

  }

  public static void highlightElementGreen(WebDriver driver, By locator) {
    try {
      if (pollForElementAndCheckIfPresent(driver, locator)) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid green'", element);
      }
    } catch (Exception e) {
      // This is a nice to have feature only hence ignore exception if there is
      // any since,
      // the element will just not be highlighted. There could be exceptions if
      // the browser
      // js is not permitting JavascriptExecutor from scripts.
    }

  }
  /*
   * Gives the start date for US
   */
  @Deprecated
  public static Calendar getStartTime() {
    Calendar now = Calendar.getInstance();
    return now;
  }

  /*
   * Gives the start date for DE
   */
  @Deprecated
  public static Calendar getDayAndDateForDE() {
    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"),
        Locale.GERMANY);
    return now;
  }

  /*
   * Gives the start date for UK
   */
  @Deprecated
  public static Calendar getDayAndDateForUK() {
    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"),
        Locale.UK);
    return now;
  }

  
  public static void doMouseActionUsingJS(String mouseEvent, By hoverOn, WebDriver webDriver) {
	    WebElement element = webDriver.findElement(hoverOn);
	    ((JavascriptExecutor) webDriver).executeScript(
	        "var event = document.createEvent('MouseEvents');" + "event.initEvent('" + mouseEvent
	            + "', true, true);" + "var element = arguments[0];" + "element.dispatchEvent(event);",
	        element);
	  }

	  public static void doMouseActionUsingJS(String mouseEvent, WebElement hoverOnElement,
	      WebDriver webDriver) {
	    ((JavascriptExecutor) webDriver).executeScript(
	        "var event = document.createEvent('MouseEvents');" + "event.initEvent('" + mouseEvent
	            + "', true, true);" + "var element = arguments[0];" + "element.dispatchEvent(event);",
	        hoverOnElement);
	  }
	  /*private static String getReportPath() {
		    String currDir = TEST_EXECUTION_DIR.replace("\\", FORWARD_SLASH);
		    StringBuilder sb = new StringBuilder();
		    if (IS_CI_RUN) {
		      sb.append(OSDetector.getJobUrl() + "ws/");
		      String[] curDirFolders = currDir
		          .split(currDir.contains(FORWARD_SLASH) ? FORWARD_SLASH
		              : BACKWARD_SLASH);
		      boolean isReportPathStart = false;
		      for (String folder : curDirFolders) {
		        if (folder.equals(OSDetector.getCIJobName())) {
		          isReportPathStart = true;
		        }
		        if (!folder.equals(OSDetector.getCIJobName()) && isReportPathStart
		            && !folder.equals("workspace")) {
		          sb.append(folder);
		          sb.append(FORWARD_SLASH);
		        }
		      }
		    } else {
		      sb.append("file:///");
		      sb.append(currDir);
		    }
		    return sb.toString();
		  }*/
	  public static String captureScreenShot(WebDriver driver) {
		    /*if ("on".equalsIgnoreCase(System.getProperty("SCREENSHOT"))) {

		      try {
		        // If the driver is RemoteWebDriver then augment it to enable screen
		        // shots on it.
		        if (driver.getClass().getName().contains("RemoteWebDriver")) {
		          driver = new Augmenter().augment(driver);
		        }
		        final DateFormat df = new SimpleDateFormat(TIME_FORMAT);
		        final DateFormat dfFolder = new SimpleDateFormat(DATE_FORMAT);
		        File scrFile = ((TakesScreenshot) driver)
		            .getScreenshotAs(OutputType.FILE);

		        String fileName = SCREEN_SHOT_DIR + dfFolder.format(new Date())
		            + FORWARD_SLASH + BROWSER + FORWARD_SLASH + df.format(new Date())
		            + SCREEN_SHOT_EXTENSION;
		        FileUtils.copyFile(scrFile, new File(fileName));
		        String link = "  <b><a href='" + REPORT_PATH + fileName
		            + "' onclick=\"window.open('" + REPORT_PATH + fileName
		            + "','popup','"
		            + "width=800,height=1500,toolbar=no,directories=no,location=no,"
		            + "menubar=no,status=no,left=0,top=0'); return false\">"
		            + "screenshot" + "</a></b>  <a href='" + driver.getCurrentUrl()
		            + "' target='_blank' >" + "    url " + "</a>";
		        return link;
		      } catch (Exception e) {
		        logFailureStep("Could not capture screenshot due to:" + e);
		      }
		    }*/
		    return "";
		  }
}
