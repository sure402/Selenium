package com.suresh.dev.utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.suresh.dev.pagecomponents.LoginPage;

public class WaitForUtility {

	  static long TIMEOUT_S = 60;
	  static int INT_TIMEOUT_S = Integer.parseInt(Long.toString(TIMEOUT_S));
	  static long WAIT_TIMEOUT = 30L;
	  static long WAIT_TIMEOUT_FOR_URL = 15L;
	  final static Logger logger = Logger.getLogger(WaitForUtility.class.getName());

	  protected static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
	    return new Function<WebDriver, WebElement>() {
	      public WebElement apply(WebDriver driver) {
	        return driver.findElement(locator);
	      }
	    };
	  }

	  /**
	   * Waits for the element to be visible until a timeout of 30 secs.
	   * 
	   * @param driver
	   * @param locator
	   */
	  public static void waitForElementToBeVisible(final WebDriver driver, final By locator)
	      throws RuntimeException {
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
	      //throw new ElementNotFoundException(locator, "Polled " + WAIT_TIMEOUT + " secs.", driver);
	    }
	  }

	  /**
	   * Waits for the element to be selected until a timeout of 30 secs.
	   * 
	   * @param driver
	   * @param locator
	   */
	  public static void waitForElementToBeSelected(final WebDriver driver, final By locator, final long pollMax)
	      throws RuntimeException {
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
	     // throw new ElementNotFoundException(locator, "Polled " + WAIT_TIMEOUT + " secs.", driver);
	    }
	  }

	  /**
	   * polls for the  parent element/locatorForParent and then polls for the child element/locatorForDesiredElement
	   *  for the element to be visible until a maximum timeout of 30 secs.
	   * 
	   * @param driver
	   * @param locator
	   */
	  public static void waitForElementUnderParentToBeVisible(final WebDriver driver, final By locatorForParent,
	      By locatorForDesiredElement)
	      throws RuntimeException {
		  LoginPage.getElement(driver, locatorForParent);
	    Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
	    try {
	      wait.until(new ExpectedCondition<WebElement>() {
	        public WebElement apply(WebDriver driver) {
	          WebElement parent = LoginPage.getElement(driver, locatorForParent);
	          // driver.switchTo().defaultContent();
	          WebElement element = parent.findElement(locatorForParent);
	          if (element.isDisplayed()) {
	            return element;
	          }
	          return null;
	        }
	      });
	    } catch (Exception e) {
	      //throw new ElementNotFoundException(locatorForDesiredElement, "Child element not found after polling " + WAIT_TIMEOUT + " secs.", driver);
	    }
	  }

	  public static WebElement waitForElementAndReturnElement(final WebDriver driver, final By locator)
	      throws RuntimeException {
	    waitForElementToBeVisible(driver, locator);
	    return driver.findElement(locator);
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
	  public static void waitForElementToBeVisible(final WebDriver driver, final By locator,
	      long timeOut) throws RuntimeException {
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
	      //captureScreenShot(driver);
	      throw new RuntimeException("Exception while waiting for " + locator + ". Exception:" + e);
	    }
	  }

	  /**
	   * Waits for the given text until timing out at 30 secs.
	   * 
	   * @param driver
	   * @param locator
	   * @param text
	   */
	  public static void waitForText(final WebDriver driver, final By locator, final String text) {
	    Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT);
	    try {
	      wait.until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver webDriver) {
	          String currentText = "";
	          try {
	            currentText = driver.findElement(locator).getText();
	          } catch (Exception e) {
	            // ignore if element is not present.
	          }
	         logger.info("Waiting for:" + text + " Found:" + currentText);
	          return currentText.contains(text);
	        }
	      });

	    } catch (Exception e) {
	      //captureScreenShot(driver);
	      throw new RuntimeException("Exception while waiting for text " + text + " in " + locator
	          + ". Exception:" + e);
	    }
	  }

	  /**
	   * Waits until the given element is either hidden or deleted.
	   * 
	   * @param locator
	   * @param timeout
	   */
	  public static void waitUntilElementDisappears(final WebDriver driver, final By locator) {
	    try {
	      (new WebDriverWait(driver, WAIT_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          try {
	            WebElement element = d.findElement(locator);
	            if(element.isDisplayed()) {
	            	logger.info(locator + " spotted..");
	              return false;
	            }
	            logger.info(locator + " disappeared..");
	            return true;
	            
	          } catch (Exception e) {
	        	  logger.info(locator + " disappeared..");
	            return true;
	          }
	          
	        }
	      });
	    }  catch (Exception e) {
	      //captureScreenShot(driver);
	      throw new RuntimeException(locator + " did not disappear after polling for " + WAIT_TIMEOUT
	          + " secs.");
	    }
	  }

	  /**
	   * Waits until the given element is either hidden or deleted.
	   * 
	   * @param locator
	   * @param timeout
	   */
	  public static void waitUntilElementDisappears(final WebDriver driver, final By locator, final long waitTimeout) {
	    try {
	      (new WebDriverWait(driver, waitTimeout)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          try {
	            WebElement element = d.findElement(locator);
	            if(element.isDisplayed()) {
	            	logger.info(locator + " spotted..");
	              return false;
	            }
	            logger.info(locator + " disappeared..");
	            return true;
	            
	          } catch (Exception e) {
	        	  logger.info(locator + " disappeared..");
	            return true;
	          }
	          
	        }
	      });
	    } catch (Exception e) {
	      //captureScreenShot(driver);
	      throw new RuntimeException(locator + " did not disappear after polling for " + waitTimeout
	          + " secs.");
	    }
	  }

	  public static void waitForTitleStartingWithString(final WebDriver driver, final String title) {
	    try {
	      (new WebDriverWait(driver, WAIT_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          return d.getTitle().startsWith(title);
	        }
	      });
	    } catch (Exception e) {
	      //captureScreenShot(driver);
	      throw new RuntimeException(title + " did not show up after polling for " + WAIT_TIMEOUT
	          + " secs.");
	    }
	  }

	  public static void waitForTitleContainingString(final WebDriver driver, final String title) {
	    try {
	      (new WebDriverWait(driver, WAIT_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          return d.getTitle().contains(title);
	        }
	      });
	    } catch (Exception e) {
	      //captureScreenShot(driver);
	      throw new RuntimeException(title + " did not show up after polling for " + WAIT_TIMEOUT
	          + " secs.");
	    }
	  }

	  public static void waitForCurrentUrlToContainString(final WebDriver driver, final String url) {
	    try {
	      (new WebDriverWait(driver, WAIT_TIMEOUT_FOR_URL)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          return d.getCurrentUrl().toLowerCase().contains(url);
	        }
	      });
	    } catch (Exception e) {
	      String currUrl = driver.getCurrentUrl();
	      if (currUrl.contains(url)) {
	        return;
	      }
	      //captureScreenShot(driver);
	      throw new RuntimeException(url + " was not present in current url: " + currUrl
	          + " after polling for " + WAIT_TIMEOUT_FOR_URL + " secs.");
	    }
	  }

	  public static void waitForCurrentUrlToContainString(final WebDriver driver, final String url, long timeout) {
	    try {
	      (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          return d.getCurrentUrl().contains(url);
	        }
	      });
	    } catch (Exception e) {
	      String currUrl = driver.getCurrentUrl();
	      if (currUrl.contains(url)) {
	        return;
	      }
	      //captureScreenShot(driver);
	      throw new RuntimeException(url + " was not present in current url: " + currUrl
	          + " after polling for " + WAIT_TIMEOUT_FOR_URL + " secs.");
	    }
	  }

	  public static void waitForCurrentUrlToMatchString(final WebDriver driver, final String url) {
	    try {
	      (new WebDriverWait(driver, WAIT_TIMEOUT_FOR_URL)).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	          return d.getCurrentUrl().trim().equals(url.trim());
	        }
	      });
	    } catch (Exception e) {
	      String currUrl = driver.getCurrentUrl().trim();
	      if (currUrl.equals(url.trim())) {
	        return;
	      }
	      //captureScreenShot(driver);
	      throw new RuntimeException(url + " did not match current url: " + currUrl
	          + " after polling for " + WAIT_TIMEOUT_FOR_URL + " secs.");
	    }
	  }

	  public static boolean pollForCurrentUrlToMatchString(final WebDriver driver, final String url, final long timeout) {
	    try {
	      WaitForUtility.waitForCurrentUrlToContainString(driver, url, timeout);
	      return true;
	    } catch (Exception e) {
	      return false;
	    }
	  }

	  public static boolean pollForCurrentUrlToContainString(final WebDriver driver, final String url, final long timeout) {
	    try {
	      WaitForUtility.waitForCurrentUrlToContainString(driver, url, timeout);
	      return true;
	    } catch (Exception e) {
	      return false;
	    }
	  }

	  /**
	   * Explicitly waits for the specified milliseconds.Use this method only if
	   * absolutely neccessary.
	   * 
	   * @param milliSeconds
	   */
	  public static void holdUntil(long milliSeconds) {
	    long waitUntilTime = System.currentTimeMillis() + (milliSeconds);
	    while (System.currentTimeMillis() < waitUntilTime) {
	      // do nothing just wait for seconds.
	    }
	  }

	  public static void waitForSeconds(WebDriver driver, int waitTime) {
	    driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	  }

	}
