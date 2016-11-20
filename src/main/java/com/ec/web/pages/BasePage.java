/**
 * 
 */
package com.ec.web.pages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.ec.web.drivers.WebDriverPageOps;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 17 Mar 2016 at 17:07:14
 *
 * ************************************************************************************************
 * </pre>
 */
public abstract class BasePage {
	
	private final By RS_SCRIPT_DATA = By.xpath("//script[contains(.,'var rs')]");
	private final By SEARCH_BOX = By.xpath("//input[contains(@name, 'searchTerm')]");
	private final String PREDICTIVE_DROPDOWN_STRING = "//li[contains(@class, 'advSecContainer')";
	private final By PREDICTIVE_DROPDOWN = By.xpath(PREDICTIVE_DROPDOWN_STRING);    
    private WebDriverPageOps pageOps;
    
    public BasePage(WebDriverPageOps pageOps){
        this.pageOps = pageOps;
    }
    
    public WebDriverPageOps getPageOps(){
        return pageOps;
    }
    
    public abstract String getPageName();
    
    public void searchForTerm(String item){
    	pageOps.openPage(pageOps.getBaseUrl() + "/web/c/?searchTerm=" + item + "&sra=oss&r=t");
    }
    
    public void typeIntoSearchBox(String query){
    	pageOps.openPage(pageOps.getBaseUrl());
    	pageOps.clearAndSendKeys(SEARCH_BOX, query);
    }
    
    public List<String> getPredictedTextItems(String categoryText){
    	By PREDICTED_PRODUCTS = By.xpath(String.format("%s and .//span[contains(text(), '%s')]]/following-sibling::li", PREDICTIVE_DROPDOWN_STRING, categoryText));
    	return pageOps.getTextFromAllElementsMatching(PREDICTED_PRODUCTS);
    }
    
    public void selectFirstPredictedItemInCat(String categoryText){
    	pageOps.click(By.xpath(String.format("%s and .//span[contains(., '%s')]]/following-sibling::li[1]", PREDICTIVE_DROPDOWN_STRING, categoryText)));
    }
    
    public String getResponseData(){
    	String scriptData = new String(getPageOps().elementToString(RS_SCRIPT_DATA));
    	return scriptData;
    }
        
    public String getScriptTagProperty(String tagName){
    	String tagNameProperty, scriptData = new String();
    	scriptData = pageOps.elementToString(RS_SCRIPT_DATA);
    	Pattern pattern = Pattern.compile(tagName + ":'(.*?)'");
    	System.out.println("Pattern : "+  pattern.toString());
    	Matcher matcher = pattern.matcher(scriptData);
    	if (matcher.find()) {
    		tagNameProperty = matcher.group(1);
    	}else{
    		tagNameProperty = "Script Tag Was Not Found";
    	}
    	System.out.println("- - - - - - Script Tag = " + tagNameProperty);
    	return tagNameProperty.toLowerCase();
    }

    public void closeBrowser(){
        getPageOps().exit();
    }

}
