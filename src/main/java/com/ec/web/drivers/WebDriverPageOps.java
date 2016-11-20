package com.ec.web.drivers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ec.utils.LoggingFactory;


/**
 * Convenience class for Webdriver operations.<br>
 * Intended for use by page object model classes when interacting with the
 * application.
 */
public class WebDriverPageOps {
	private static Logger logger = new LoggingFactory().createLogger("webdriver");

	private static final long DEFAULT_IMPLICIT_TIMEOUT_SECONDS = 30;
	private static final String INPUT = "input";
	private static final String VALUE = "value";
	private final WebDriver driver;
	private final long implicitTimeout;
	private final TimeUnit implicitTimeoutTimeUnit;
	
	private String baseUrl = null;

	public WebDriverPageOps(WebDriver driver, String baseUrl) {
		this(driver, DEFAULT_IMPLICIT_TIMEOUT_SECONDS, TimeUnit.SECONDS, baseUrl);
	}

	public WebDriverPageOps(WebDriver driver, long implicitTimeout, TimeUnit implicitTimeoutTimeUnit, String baseUrl) {
		this.driver = driver;
		this.implicitTimeout = implicitTimeout;
		this.implicitTimeoutTimeUnit = implicitTimeoutTimeUnit;
	    this.baseUrl=baseUrl;
	    System.out.println(baseUrl);
		setImplicitTimeout(implicitTimeout, implicitTimeoutTimeUnit);
	}
	
	public String getBaseUrl(){
	    return baseUrl;
	}
	
	public String getPageSource(){
		return driver.getPageSource();
	}

	private void setImplicitTimeout(long implicitTimeout, TimeUnit implicitTimeoutTimeUnit) {
		logger.info("Setting implicit timeout [{%s} {%s}]", implicitTimeout, implicitTimeoutTimeUnit.toString());
		driver.manage().timeouts().implicitlyWait(implicitTimeout, implicitTimeoutTimeUnit);
	}

	public boolean elementExists(By identifier) {
	    logger.info("Checking if element exists [{%s}]", identifier);
		return elementExists(identifier, 0, null);
	}

	public boolean elementExists(By identifier, long timeoutPeriod, TimeUnit timeUnit) {
		try {
			if (timeoutPeriod > 0) {
				setImplicitTimeout(timeoutPeriod, timeUnit);
			}

			boolean result = driver.findElements(identifier).size() > 0;
			logger.info("Element [{%s}] was{%s} found", identifier, result ? "" : " not");
			return result;

		} finally {
			if (timeoutPeriod > 0) {
				resetImplicitTimeOut();
			}
		}
	}

	private void resetImplicitTimeOut() {
		setImplicitTimeout(implicitTimeout, implicitTimeoutTimeUnit);
	}

	// ==================================== SWITCHING TO THINGS ==================================

	public void switchToActiveElement() {
		logger.info("Switching to Active Element");
		driver.switchTo().activeElement();
	}


	public void switchToAlert() {
		logger.info("Switching to Alert");
		driver.switchTo().alert();
	}


	public void switchToDefaultContent() {
		logger.info("Switching to Default Content");
		driver.switchTo().defaultContent();
	}

	/**
	 * Switch focus to a specified frame.
	 * The frame is identified by its (zero-based) index.
	 * 
	 * @param id
	 *            the (zero-based) index identifying the target frame
	 */
	public void switchToFrame(int id) {
		logger.info("Switching to frame by id: [{%s}]", id);
		driver.switchTo().frame(id);
	}

	/**
	 * Switch focus to a specified frame.
	 * The frame is identified by its name or ID.
	 * 
	 * @param id the name or ID identifying the target frame
	 */
	public void switchToFrame(String id) {
		logger.info("Switching to frame by id: [{%s}]", id);
		driver.switchTo().frame(id);
	}

	public void switchToFrame(By identifier) {
		logger.info("Switching to frame [{%s}]", identifier);
		driver.switchTo().frame(driver.findElement(identifier));
	}
	
	
	public String getNameOfFirstIFrame() {
	    final WebElement iframe = driver.findElement(By.tagName("iframe"));
        String iframeName = iframe.getAttribute("name");
        return iframeName;
	}
	
	public String switchToActiveWindow(){
	 // Store the current window handle
	    String winHandleBefore = driver.getWindowHandle();
        logger.info("Switching to active window");
	    for(String winHandle : driver.getWindowHandles()){
	        logger.info("window title [{%s}]",driver.getTitle());
	        if (winHandle != winHandleBefore){
	            logger.info("Switching to active window with name [{%s}]", winHandle);
	            driver.switchTo().window(winHandle);
	        }
	    }
	    
	    return winHandleBefore;
	}
	
	public void switchToWindowHandel(String WindowHandlename){
        logger.info("Switching to window [{%s}]", WindowHandlename);
	    driver.switchTo().window(WindowHandlename);
	}
	
	
	//============================================ SCROLLING ======================================================
	public void scrollDown(){
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
	}
	
	public void putElementIntoView(By indentifier){
	    logger.info("scrolling to put element with identifer [{%s}] in view",indentifier);
    	WebElement element = driver.findElement(indentifier);
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("arguments[0].scrollIntoView(true);", element);
    	logger.info("element with identifer [] now in view",indentifier);
    	SleepInCaseOfDireCircumstances(500);
	}
	

	// ==========================================GENERAL OPERATIONS================================================

	public void refreshPage(){
	    logger.info("Refreshing page");
	    driver.navigate().refresh();
	}
	
	public void openPage(String url) {
		logger.info("Opening page [{%s}]", url);
		driver.navigate().to(url);
	}
	
	public void clearAndSendKeys(By identifier, String text, int NthElement) {
		logger.info("Clear [{%s}] and send keys [{%s}] to the nth elem in list", identifier, text, NthElement);
		List<WebElement> listElems = driver.findElements(identifier);
		listElems.get(NthElement).clear();
		listElems.get(NthElement).sendKeys(text);
	}
	

	public void clearAndSendKeys(By identifier, String text){
		logger.info("Clear [{%s}] and send keys [{%s}]", identifier, text);
		driver.findElement(identifier).clear();
		driver.findElement(identifier).sendKeys(text);
	}

	public void click(By identifier) {
		logger.info("Click [{%s}]", identifier);
		driver.findElement(identifier).click();
	}

	public void selectOptionByText(By identifier, String text) {
		logger.info("Select [{%s}] from [{%s}]", text, identifier);
		Select select = new Select(driver.findElement(identifier));
		select.selectByVisibleText(text);
	}
	
   public void selectOptionByText(WebElement elem, String text) {
        logger.info("Select [{%s}] from [{%s}]", text, elem);
        Select select = new Select(elem);
        select.selectByVisibleText(text);
    }

	public String getElementText(By identifier) {
		WebElement element = driver.findElement(identifier);
		System.out.println(getText(element));
		String text = getText(element);
		logger.info("Element [{%s}] contains text [{%s}]", identifier, text);
		return text;
	}

	private String getText(WebElement element) {
		String text = "";

		if (INPUT.equalsIgnoreCase(element.getTagName())) {
			text = element.getAttribute(VALUE);

		} else {
			text = element.getText();
		}

		return text;
	}

	public List<String> getTextFromAllElementsMatching(By identifier) {
		logger.info("Getting text from all elements matching [{%s}]", identifier);
		List<String> result = new LinkedList<String>();
		for (WebElement element : driver.findElements(identifier)) {
			String elementText = getText(element);
			result.add(elementText);
		}

		return result;
	}
	
	public String elementToString(By identifier){
		logger.info("Turning [{%s}] element into a string.", identifier);
		WebElement htmlElement =  driver.findElement(identifier);
		String scriptText = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML", htmlElement);
		return scriptText;
	}

	/**
	 * @param identifier
	 * @param expectedText
	 * @return <code>true</code> if the expected text (ignoring case) is found
	 *         within the text of all elements matching the given identifier,
	 *         <code>false</code> otherwise.
	 */
	public boolean allMatchingElementsContainText(By identifier, String expectedText) {
		if (expectedText != null) {
			logger.info("Checking if all elements identified by [{%s}] contain [{%s}]", identifier, expectedText);

			List<String> values = getTextFromAllElementsMatching(identifier);
			logger.info("[{%s}] elements found matching [{%s}]", values.size(), identifier);

			for (String value : values) {
				if (!value.toLowerCase().contains(expectedText.toLowerCase())) {
					logger.warn("Value [{%s}] does not contain [{%s}]", value, expectedText);
					return false;
				}
			}
		}

		return true;
	}

	public boolean elementIsNotDisplayed(By identifier) {
		return !driver.findElement(identifier).isDisplayed();
	}

	public void deleteCookies() {
		logger.info("Deleting cookies...");
		driver.manage().deleteAllCookies();
	}

	public void takeScreenShot(File outputFile) {
		try {
			logger.info("Taking screenshot...");
			byte[] imageData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			FileUtils.writeByteArrayToFile(outputFile, imageData);
			logger.info("Screenshot saved as [{%s}]", outputFile.getPath());
		} catch (Exception e) {
			logger.error("Exception attempting to capture screen shot", e);
		}
	}

	public void exit() {
		logger.info("Exiting webDriver...");
		driver.quit();
	}

	public String FindElementByIDAndReturnAttribute(String id, String attribute) {
		WebElement elem = driver.findElement(By.id(id));
		String attributeValue = elem.getAttribute(attribute);
		return attributeValue;
	}
	
   public String FindElementByAndReturnAttribute(By indentifier, String attribute) {
        WebElement elem = driver.findElement(indentifier);
        String attributeValue = elem.getAttribute(attribute);
        return attributeValue;
    }
	
	public String getAttributeFromElem(WebElement elem, String attribute){
		String attributeValue = elem.getAttribute(attribute);
		logger.info("{%s}",attribute);
		return attributeValue;
	}
	
	public String getAttributeFromElem(By indentifier, String attribute){
        WebElement elem = driver.findElement(indentifier);
        String attributeValue = elem.getAttribute(attribute);
        logger.info("{%s}",attribute);
        return attributeValue;
    }
	
	public void acceptAlert(){
		SleepInCaseOfDireCircumstances(400);
		driver.switchTo().alert().accept();
		SleepInCaseOfDireCircumstances(400);
		
	}
	
	public List<String> getListOfAttributeFromElems(By indentifier, String attribute){
	    LinkedList listOfAttribute = new LinkedList<String>();
	    List<WebElement> webElementList= driver.findElements(indentifier);
	    for(WebElement elem : webElementList){
	        String attributeValue = elem.getAttribute(attribute);
	        listOfAttribute.add(attributeValue);
	    }
	    return listOfAttribute;
	}
	
   public String getUrl(){
        String url = driver.getCurrentUrl();
        logger.info("found url {%s}", url);
        return url;
    }
	
	//========================================== COUNT THINGS ===============================================================
	
	public int countNumberOfElements(By indentifier){
		logger.info("Counting Number of elements by identifier {%s}",indentifier);
		List<WebElement> elems = driver.findElements(indentifier);
		int countelems = elems.size();
		return countelems;
	}
	
	//========================================== ADVANCED COMPOSITE ACTIONS===================================================
	
	public void dragAndDrop(By byElement, WebElement target){
		logger.info("drag and Drop element. from :- {%s}  to {%s}",byElement, target);
		WebElement element = driver.findElement(byElement);
		//WebElement target = driver.findElement(byTarget);
		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void dragAndDrop(By byElement, By target){
		logger.info("drag and Drop element. from :- {%s}  to {%s}",byElement, target);
		WebElement element = driver.findElement(byElement);
		WebElement targetelem = driver.findElement(target);
		//WebElement target = driver.findElement(byTarget);
		(new Actions(driver)).dragAndDrop(element, targetelem).perform();
		SleepInCaseOfDireCircumstances(400);
		click(byElement);
	}
	
	//========================================= RETURN ELEMENTS ================================================================
	
	public WebElement getNthElement(By identified, int n){
		List<WebElement> elems = driver.findElements(identified);
		WebElement elem = elems.get(n);
		return elem;
	}
	
	public List<WebElement> getElements(By identified){
		List<WebElement> elems = driver.findElements(identified);
		return elems;
	}
	
	//========================================= CHANGE RETURNED ELEMENTS ================================================================
	
	public String removeHtmlTagsFromString(String stringWithTags){
		String stringNoTags = stringWithTags.replaceAll("(<[^>]*>)", "");
		return stringNoTags;
	}
	
	// ======================================== WAIT FOR THINGS ================================================================

	public void waitForElementToBeVisible(By identifier) {
		waitForElementToBeVisible(identifier, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	}

	public void waitForElementToBeVisible(By identifier, long timeoutSeconds) {
		try {
			logger.info("Waiting for element [{%s}] to be visible...", identifier);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(elementExistsAndIsVisible(identifier));
			logger.info("Element [{%s}] is now visible", identifier);

		} catch (TimeoutException e) {
			logger.warn("Element [{%s}] was not visible within timeout period [{%s}]", identifier, timeoutSeconds);
		}
	}
	
	   public boolean checkIfElementIsVisible(By identifier, long timeoutSeconds) {
	        try {
	            logger.info("Waiting for element [{%s}] to be visible...", identifier);
	            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
	            wait.until(elementExistsAndIsVisible(identifier));
	            logger.info("Element [{%s}] is now visible", identifier);
	            return true;
	        } catch (TimeoutException e) {
	            logger.warn("Element [{%s}] was not visible within timeout period [{%s}]", identifier, timeoutSeconds);
	            return false;
	        }
	    }

	public void waitForElementToBeClickable(By identifier) {
		waitForElementToBeClickable(identifier, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	}

	public void waitForElementToBeClickable(By identifier, long timeoutSeconds) {
		try {
			logger.info("Waiting for element [{%s}] to be clickable...", identifier);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(identifier));
			logger.info("Element [{%s}] is now clickable", identifier);

		} catch (TimeoutException e) {
			logger.warn("Element [{%s}] was not clickable within timeout period [{%s}]", identifier, timeoutSeconds);
		}
	}
	
   public boolean checkIfElementToBeClickable(By identifier, long timeoutSeconds) {
        try {
            logger.info("Waiting for element [{%s}] to be clickable...", identifier);
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(identifier));
            logger.info("Element [{%s}] is now clickable", identifier);
            return true;

        } catch (TimeoutException e) {
            logger.warn("Element [{%s}] was not clickable within timeout period [{%s}]", identifier, timeoutSeconds);
            return false;
        }
    }

	public void waitForElementToBeClickableAndVisable(By identifier) {
		waitForElementToBeVisible(identifier, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
		waitForElementToBeClickable(identifier, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	}

	private ExpectedCondition<Boolean> elementExistsAndIsVisible(final By identifier) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(identifier);
					return element.isDisplayed();

				} catch (NoSuchElementException e) {
					return false;
				}
			}
		};
	}

	public void waitForElementNotToBeVisible(By identifier) {
		waitForElementNotToBeVisible(identifier, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	}

	public void waitForElementNotToBeVisible(By identifier, long timeoutSeconds) {
		try {
			logger.info("Waiting for element [{%s}] not to be visible...", identifier);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(elementDoesNotExistOrIsNotVisible(identifier));
			logger.info("Element [{%s}] is no longer visible", identifier);

		} catch (TimeoutException e) {
			logger.warn("Element [{%s}] was still visible after timeout period [{%s}]", identifier, timeoutSeconds);
		}
	}

	private ExpectedCondition<Boolean> elementDoesNotExistOrIsNotVisible(final By identifier) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(identifier);
					return !element.isDisplayed();

				} catch (NoSuchElementException e) {
					return true;
				}
			}
		};
	}
	
	public void waitForFrameToBeVisable(String frameName){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	public void waitForFrameToBeVisable(By identifier){
	        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(identifier));
	}

	/**
	 * USE IN DIRE CIRCUMSTANCES AND QUESTION WHY?
	 * 
	 * @param milliseconds
	 *            :- 1000 = 1 sec
	 */
	public void SleepInCaseOfDireCircumstances(long milliseconds) {
		try {
			logger.info("Sleeping for {%s}",milliseconds);
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void waitForElementToContainText(By identifier, String expectedText) {
		waitForElementToContainText(identifier, expectedText, DEFAULT_IMPLICIT_TIMEOUT_SECONDS);
	}

	public void waitForElementToContainText(By identifier, String expectedText, long timeoutSeconds) {
		try {
			logger.info("Waiting for element [{%s}] to contain text [{%s}]...", identifier, expectedText);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(elementContainsText(identifier, expectedText));
			logger.info("Element [{%s}] contains text [{%s}]", identifier, expectedText);

		} catch (TimeoutException e) {
			logger.warn("Element [{%s}] does not contain text [{%s}] after timeout period", identifier, expectedText);
		}
	}

	private ExpectedCondition<Boolean> elementContainsText(final By identifier, final String expectedText) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return getText(driver.findElement(identifier)).contains(expectedText);
			}
		};
	}
	
	//LOGGING
	
	public void logInfo(String message){
	    logger.info(message);
	}

}
