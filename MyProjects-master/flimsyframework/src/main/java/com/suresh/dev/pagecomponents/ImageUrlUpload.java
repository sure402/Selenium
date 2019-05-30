package com.suresh.dev.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.suresh.dev.pagelocators.ImageUrlUploadLocators;
import com.suresh.dev.utils.ActionUtility;


public class ImageUrlUpload extends ImageUrlUploadLocators {

	private String imageURL = "http://i.ebayimg.qa.test.com/00/s/MjAwWDc0MA==/$T6BCp,Fshg8E-gWD5SVcBR(vsj35JQ~~60_43.JPG?set_id=80000000000";
	private String destinationURL = "http://www.qa.test.com";
		
	public ImageUrlUpload(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public ImageUrlUpload(WebDriver driver) {
		super(driver);
	}
	
	public enum UploadDataType{
		EvtGrpCommonBannerImageURL,
		EvtGrpCommonBannerDestinationURL,
		EvtGrpBrowseButtonImageURL,
		EvtGrpBrowseButtonDestinationURL,
		DesktopimageURL,
		MobileimageURL,
	}
	
	/**
	 * @param type
	 * Enter URL. 
	 */
	public void enterLink(UploadDataType type,By locator) {	
	    try {
	    	switch(type){
	    	case EvtGrpCommonBannerImageURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(imageURL);
	    		break;
	    	case EvtGrpCommonBannerDestinationURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(destinationURL);
	    		break;
	    	case EvtGrpBrowseButtonImageURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(imageURL);
	    		break;
	    	case EvtGrpBrowseButtonDestinationURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(destinationURL);
	    		break;
	    	case DesktopimageURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(destinationURL);
	    		break;	
	    	case MobileimageURL :
	    		ActionUtility.getElement(driver, locator).sendKeys(destinationURL);
	    		break;	
	    	}
	    } catch (Exception e) {
	      throw new RuntimeException("GetElement and entering "+ type.name() +" failed: " 
	          + e.getMessage());
	    }   
	}
	
	
	/**
	 * Check if uploaded data is saved as expected
	 * @param type
	 * @return
	 */
	public boolean isUploadDataSaved(UploadDataType type,By locator){
		boolean flag = true;
	    try {
	    	switch(type){
	    	case EvtGrpCommonBannerImageURL :
	    		if(ActionUtility.getElement(driver, locator).getAttribute("value").isEmpty())
	    			flag = false; 
	    		break;
	    	case EvtGrpCommonBannerDestinationURL :
	    		if(ActionUtility.getElement(driver, locator).getAttribute("value").isEmpty())
	    			flag = false; 
	    		break;
	    	case EvtGrpBrowseButtonImageURL :
	    		if(ActionUtility.getElement(driver, locator).getAttribute("value").isEmpty())
	    			flag = false; 
	    		break;
	    	case EvtGrpBrowseButtonDestinationURL :
	    		if(ActionUtility.getElement(driver, locator).getAttribute("value").isEmpty())
	    			flag = false; 
	    		break;
	    	}
	    } catch (Exception e) {	
		      throw new RuntimeException("GetElement for "+ type.name() +" failed: " 
			          + e.getMessage());
	    } 
	    return flag;
	}
	

}
