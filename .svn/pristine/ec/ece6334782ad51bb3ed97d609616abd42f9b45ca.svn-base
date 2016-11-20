/**
 * 
 */
package com.ec.web.pages;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ec.web.containers.Category;
import com.ec.web.drivers.WebDriverPageOps;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 21 Mar 2016 at 16:59:51
 *
 * ************************************************************************************************
 * </pre>
 */
public class MultiCategoryPage extends BasePage{
    
    //GENERAL COUNTS
    private final By NUMBER_OF_PRODUCTS = By.id("totalproducts");
    private final By NUMBER_OF_CATEGORIES = By.id("totalfamilies");
    
    //POPULAR CATEGORIES
    private final By POPULAR_CATEGORIES_COUNT = By.cssSelector("ul.popularCategoryList li");
    private final By POPULAR_CATEGORIES_ANCHOR_LIST = By.cssSelector("ul.popularCategoryList li a");
    private final By POPULAR_CATEGORIES_IMG_LIST = By.cssSelector("ul.popularCategoryList li img");
    
    //ALL PRODUCTS
    private final By ALL_PRODUCTS_LIST = By.cssSelector("tr.resultRow td.descColHeader a.primarySearchLink:first-child");
    
    public MultiCategoryPage(WebDriverPageOps pageOps){
        super(pageOps);
    }
    
    public Integer getNumberOfCategories(){
        return Integer.valueOf(getPageOps().getElementText(NUMBER_OF_CATEGORIES));   
    }
    
    public String getNumberOfProducts(){
        return getPageOps().getElementText(NUMBER_OF_PRODUCTS);
    }
    
    /**
     * Creates a list of most popular categories on the page
     * @code:Needs to be rewritten to make more efficient
     * @return: List<Category>
     */
    public ConcurrentLinkedQueue<Category> getMostPopularCategories(){
        ConcurrentLinkedQueue<Category> popularCategoryQueue= new ConcurrentLinkedQueue<Category>();
        Integer categoryCount = getPageOps().countNumberOfElements(POPULAR_CATEGORIES_COUNT);
        for(int i=0; i<categoryCount || i<10; i++){
            WebElement categoryAnchorElem = getPageOps().getNthElement(POPULAR_CATEGORIES_ANCHOR_LIST, i);
            WebElement categoryImgElem = getPageOps().getNthElement(POPULAR_CATEGORIES_IMG_LIST, i); 
            String categoryName = getPageOps().getAttributeFromElem(categoryAnchorElem, "title");
            String categoryHref = getPageOps().getAttributeFromElem(categoryAnchorElem, "href");
            String categoryImg = getPageOps().getAttributeFromElem(categoryImgElem, "src");
            Category category = new Category(categoryName, categoryHref, categoryImg);
            popularCategoryQueue.add(category);
        }
        return popularCategoryQueue;
    }
    
    /**
     * Returns up to ten RS Numbers of products on page
     * If less than 10 exists return between 0-10
     */
    private ConcurrentLinkedQueue<String> getTopTenItemsRSNumberFromAllProducts(){
        ConcurrentLinkedQueue<String> allItemsQueue = new ConcurrentLinkedQueue<String>();
        getPageOps().getListOfAttributeFromElems(ALL_PRODUCTS_LIST, "href");
        return allItemsQueue;
        
    }

    /* (non-Javadoc)
     * @see com.ec.web.pages.BasePage#getPageName()
     */
    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return null;
    }
}
