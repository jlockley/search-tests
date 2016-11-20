/**
 * 
 */
package com.ec.web.scrappers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;
import com.ec.containers.test.TestCasesContainer;
import com.ec.web.drivers.WebDriverPageOps;
import com.ec.web.pages.BasePage;
import com.ec.web.pages.PageDiscovery;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 18 Mar 2016 at 11:29:49
 *
 * ************************************************************************************************
 * </pre>
 */
public class ItemSearcherScrapper extends Scrapper implements Runnable{
    
    private TestCasesContainer testCases;
    private PageDiscovery discoverPage;
    private static final Logger LOGGER = LogManager.getLogger(ItemSearcherScrapper.class);
    
    public ItemSearcherScrapper(WebDriverPageOps webDriverPageOps, TestCasesContainer startData){
        super(webDriverPageOps);
        this.testCases = startData;
        this.discoverPage = new PageDiscovery(webDriverPageOps);
     }

    @Override
    public void run(){
        
        boolean interupted = false;
        TestCase testCase = null;
        String foundItem = null;
        int loopcount = 0;
        
        while(interupted == false){
            try{
                try{
                    testCase = testCases.getTestFromQueue();
                    if(testCase == null){
                        throw new IllegalStateException("No test case found");
                    }
                    List<Query> queries = testCase.getQuerys();
                    for(Query query: queries){
                        String searchTerm = query.getQuery();
                        searchForItem(searchTerm);
                    }
                }catch(IllegalStateException e){
                   System.out.println("Waiting for more data to hit list");
                   Thread.sleep(2000);
                   loopcount++;
                   if(loopcount >= 2){
                       interupted = true;
                   }
                   
                } 
            }catch (InterruptedException e1) {
                if(foundItem != null){
                    //add item to list before exiting
                }
                else{
                    testCases.putTestInQueue(testCase);
                }   
            }
        }
    }
        
    private void searchForItem(String item){
        discoverPage.searchForTerm(item);
        BasePage returnedPage = discoverPage.discoverPage();
        LOGGER.info(returnedPage.getPageName());
    }
    
    private void scrapeInfo(){
        
    }
    
    private Integer getNumberOfCategories(){
        return 1;
    }
}
