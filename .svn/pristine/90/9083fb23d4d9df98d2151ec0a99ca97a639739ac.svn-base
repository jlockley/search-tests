/**
 * 
 */
package com.ec.web.scrappers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.ec.containers.test.TestCasesContainer;
import com.ec.web.drivers.WebDriverPageOps;
import com.ec.web.pages.BasePage;
import com.ec.web.pages.PageDiscovery;
import com.ec.web.pages.SearchResultsPage;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 6 Apr 2016 at 16:17:39
 *
 * ************************************************************************************************
 * </pre>
 */
public class NoThreadGeneralWebBot extends Scrapper{

    
    private TestCasesContainer testCases;
    private SearchResultsPage landingPage;
    private PageDiscovery discoveryPage;
    private BasePage basePage;
    
    public NoThreadGeneralWebBot(WebDriverPageOps webDriverPageOps){
        super(webDriverPageOps);
        this.landingPage = new SearchResultsPage(webDriverPageOps);
        this.basePage = new BasePage(webDriverPageOps){
			@Override
			public String getPageName() {
				// TODO Auto-generated method stub
				return null;
			}
		};
     }

    @Override
    public void run(){}
    
    public Integer getCategoriesCountForItem(String item, Boolean exitDriverAfterwards){
    	basePage.searchForTerm(item);
        Integer numberOfCategories = landingPage.getNumberOfCategories();
        tearDown(exitDriverAfterwards);
        return numberOfCategories;  
    }
    
    public String getScriptTagContentsFromSearch(String query, String tagName, Boolean exitDriverAfterwards){
    	basePage.searchForTerm(query);
//    	BasePage returnPage = discoveryPage.discoverPage();
//    	System.out.println(returnPage.getPageName());
    	String tagProperty = landingPage.getScriptTagProperty(tagName);
    	tearDown(exitDriverAfterwards);
        return tagProperty;
    }
    
    public List<String> getPredictedDropdownItems(String query, Boolean exitDriverAfterwards, String categoryText){
    	basePage.typeIntoSearchBox(query);
    	List<String> itemsWithTags = basePage.getPredictedTextItems(categoryText);
    	List<String> itemsWithoutTags = new ArrayList<String>();
    	for (String item : itemsWithTags){
    		itemsWithoutTags.add("0" + landingPage.getPageOps().removeHtmlTagsFromString(item));
    	}
    	tearDown(exitDriverAfterwards);
    	return itemsWithoutTags;
    }
    
    public String getScriptTagContentsFromPredictiveSearch(String query, Boolean exitDriverAfterwards, String tagName, String categoryText){
    	basePage.typeIntoSearchBox(query);
    	landingPage.selectFirstPredictedItemInCat(categoryText);
    	String tagProperty = landingPage.getScriptTagProperty(tagName);
    	tearDown(exitDriverAfterwards);
        return tagProperty;
    }
    
    private void tearDown(Boolean exitDriver){
        if(exitDriver){
            landingPage.closeBrowser();
        }
    }
            
    private Integer getNumberOfCategories(){
        return 1;
    }
    
    
}
