/**
 * 
 */
package com.ec.web.pages;

import org.openqa.selenium.By;

import com.ec.web.drivers.WebDriverPageOps;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 21 Mar 2016 at 16:42:17
 *
 * ************************************************************************************************
 * </pre>
 */
public class PageDiscovery extends BasePage {

    public PageDiscovery(WebDriverPageOps pageOps){
		super(pageOps);
	}

	//Identifiers
    private final By CATEGORY_IDENTIFIER = By.xpath("//div[contains(@id, 'outerwrapper') and .//div[contains(@id, 'categories')]]//div[contains(@id,'breadcrumb')]//li");
    private final By NODE_IDENTIFIER = By.xpath("//div[contains(@id, 'outerwrapper') and .//div[contains(text(), 'Matches')]]//div[contains(@id,'breadcrumb')]//li");
    private final By FILTERED_IDENTIFIER = By.xpath("//div[contains(@id, 'outerwrapper') and .//div[contains(@class, 'appliedFiltersSection')]]//div[contains(@id, 'breadcrumb')]//li");
    private final By LINE_LEVEL_IDENTIFIER = By.cssSelector("div.advLineLevelContainer");
    private final By SEARCH_PAGE_IDENTIFIER = By.xpath("//h1[contains(@class, 'searchResults') and .//span[contains(@id, 'totalproducts')] and .//span[contains(@id, 'totalfamilies')]]");
    private final By BRAND_PAGE_IDENTIFIER = By.cssSelector("div.brandLogoDiv");
    
    public BasePage discoverPage(){
    	if(getPageOps().countNumberOfElements(CATEGORY_IDENTIFIER) == 3){
            return new SuperCategoryPage(getPageOps());
        }else if(getPageOps().countNumberOfElements(CATEGORY_IDENTIFIER) > 3){
            return new SubCategoryPage(getPageOps());
        }else if(getPageOps().countNumberOfElements(NODE_IDENTIFIER) > 3){
            return new TerminalNodePage(getPageOps());
        }else if(getPageOps().countNumberOfElements(FILTERED_IDENTIFIER) > 3){
        	return new FilteredNodePage(getPageOps());
    	}else if(getPageOps().countNumberOfElements(NODE_IDENTIFIER) > 3 && getPageOps().getUrl().contains("?searchTerm=")){
        	return new SearchWithinNodePage(getPageOps());
    	}else if(getPageOps().elementExists(LINE_LEVEL_IDENTIFIER)){
        	return new LineLevelPage(getPageOps());
    	}else if(getPageOps().elementExists(SEARCH_PAGE_IDENTIFIER)){
    		return new SearchResultsPage(getPageOps());
    	}else if(getPageOps().elementExists(BRAND_PAGE_IDENTIFIER)){
    		return new BrandPage(getPageOps());
    	}else if(getPageOps().checkIfElementIsVisible(CATEGORY_IDENTIFIER, 4) && getPageOps().checkIfElementIsVisible(BRAND_PAGE_IDENTIFIER, 4)){
    		return new BrandNodePage(getPageOps());
    	}else{
            throw new IllegalStateException("Page type is not recognised, 1) add new page, 2)Check identifiers in Page Discovery still valid");
        }
    }

	@Override
	public String getPageName() {
		return "PageDiscovery";
	}
    
    

}
