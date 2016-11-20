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
public class TestSearchConfigTagExists {
   

    private String locale;
    private String query;
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    private ScrapperFactory scrapperFactory;
    
    
    private final static String ENV = "st2";
    private final static List<String> EXPECTED_PROPERTIES = (List<String>) Lists.newArrayList("1","0");

    @Before
    public void initialize() {
        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
    }

    public TestSearchConfigTagExists(String locale, String query) {
        this.locale = locale;
        this.query = query; 
   }

    @Parameterized.Parameters(name="search_config Tag Exists for Locale: {0} for Query: {1}")
    public static Collection<Object[]> createTestData(){
        
        return Arrays.asList(new Object[][] {
//            { "uk", "fet" },
        	{ "uk", "vinyl connectors" },
//            { "uk", "RAWLBOLT"},
//            { "uk", "FET" }, 
            { "uk", "led light bulb"},
            { "uk", "CAT6 Cable" },
        	{ "uk", "12V Battery" },
            { "uk", "1nf capacitor"},
            
        });
    }


@Test
   public void testSearchConfigTagExists() {
       System.out.println("Running test for locale : " + locale);
       String tagProperty = getScriptTagProperty(query, "search_config", locale);
       Assert.assertTrue(String.format("Tag property: {%s}, is not within Expected Properties", tagProperty), (EXPECTED_PROPERTIES.contains(tagProperty)));
       System.out.println("----------------------------------END TEST---------------------------------");    
   }
   
   private String getScriptTagProperty(String query, String scriptTag, String locale){
	   NoThreadGeneralWebBot webBot = scrapperFactory.getNonThreadGeneralBot(locale, ENV);
       String tagProperty = webBot.getScriptTagContentsFromSearch(query, scriptTag, true);
       return tagProperty;
   }
}
       
    

