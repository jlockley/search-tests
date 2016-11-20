/**
 * 
 */
package com.rs_search_relevancy.test_search.analytic_tagging;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.scrappers.NoThreadGeneralWebBot;
import com.ec.web.scrappers.ScrapperFactory;
import com.google.common.collect.Lists;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 24 Mar 2016 at 11:12:19
 *
 * ************************************************************************************************
 * </pre>
 */

@RunWith(Parameterized.class)
public class TestPredictiveCategorySelection {
   

    private String searchType;
    private String query;
    private String category;
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    private ScrapperFactory scrapperFactory;
    
    
    private final static String ENV = "st2";
    private final static String LOCALE = "de";
//    private final static List<String> EXPECTED_PROPERTIES = (List<String>) Lists.newArrayList("1","0");

    @Before
    public void initialize() {
        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
    }

    public TestPredictiveCategorySelection(String searchType, String category, String query) {
        this.searchType = searchType;
        this.query = query; 
        this.category = category; 
   }

    @Parameterized.Parameters(name="Test Search Action is {0}, when searching {1}: {2}")
    public static Collection<Object[]> createTestData(){
        
        return Arrays.asList(new Object[][] {
            { "Predictive|MPN", "Herst. Teile-Nr.", "123" },
            { "Predictive|Category", "Kategorie", "123"},
            { "Predictive|Brand", "Marke", "123"},
            { "Predictive|STOCKNUM", "RS Best.-Nr.", "123"},
        });
    }

@Test
   public void testSearchResponseAction() {
       System.out.println("Test - query: " + query + " search_type should be: " + searchType);
       String tagProperty = getScriptTagProperty(query, "search_response_action");
       Assert.assertTrue(String.format("Search Type was: {%s} but should have been: {%s}", tagProperty, searchType), tagProperty.equals(searchType.toLowerCase()));
       System.out.println("----------------------------------END TEST---------------------------------");    
   }
   
   private String getScriptTagProperty(String query, String scriptTag){
	   NoThreadGeneralWebBot webBot = scrapperFactory.getNonThreadGeneralBot(LOCALE, ENV);
       String tagProperty = webBot.getScriptTagContentsFromPredictiveSearch(query, true, scriptTag, category);
       return tagProperty;
   }
}
       
    

