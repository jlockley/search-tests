package com.rs_search_relevancy.test_search.analytic_tagging;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.scrappers.NoThreadGeneralWebBot;
import com.ec.web.scrappers.ScrapperFactory;

@RunWith(Parameterized.class)
public class TestSearchBrowseJourneys {
    private String configTag;
    private String query;
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    private ScrapperFactory scrapperFactory;
    
    
    private final static String ENV = "st2";
    private final static String LOCALE = "uk";
    private final static String CATEGORY_IN_LANGUAGE = ".";
//    private final static List<String> EXPECTED_PROPERTIES = (List<String>) Lists.newArrayList("1","0");

    @Before
    public void initialize() {
        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
    }

    public TestSearchBrowseJourneys(String searchType, String query) {
        this.configTag = searchType;
        this.query = query; 
   }

    @Parameterized.Parameters(name="Search Type is {0}, when searching {1}")
    public static Collection<Object[]> createTestData(){
        
        return Arrays.asList(new Object[][] {
            { "SEARCH_BROWSE", "RJ45" },
            { "SEARCH_BROWSE", "barrier" },
            { "KEYWORD_SINGLE_ALPHA", "strip" },
            { "KEYWORD_SINGLE_ALPHA_NUMERIC", "CAT6" },
            { "KEYWORD_MULTI_ALPHA_NUMERIC", "RJ 45" },
            { "KEYWORD_MULTI_ALPHA", "barrier strip" },
            { "KEYWORD_MULTI_ALPHA_NUMERIC", "CAT 6" },
            { "KEYWORD_SINGLE_OR_MULTI_NUMERIC", "854-1"},
            { "RS_STOCK_NUMBER", "854-1001"}
        });
    }

@Test
   public void testSearchType() {
       System.out.println("Test - query: " + query + " search_browse should be: " + configTag);
       NoThreadGeneralWebBot webBot = scrapperFactory.getNonThreadGeneralBot(LOCALE, ENV);
       TestScriptTagPropertyFromSearch(query, configTag.toLowerCase(), webBot);
       testScriptTagPropertyFromBrowse(query, configTag.toLowerCase(), webBot);
       System.out.println("----------------------------------END TEST---------------------------------");    
   }
   
   private void TestScriptTagPropertyFromSearch(String query, String scriptTag, NoThreadGeneralWebBot webBot){ 
       String tagProperty = webBot.getScriptTagContentsFromSearch(query, scriptTag, true);
       Assert.assertTrue(String.format("search_browse was: {%s} but should have been: {%s}", tagProperty, configTag), tagProperty.equalsIgnoreCase("SEARCH"));
   }
   
   private void testScriptTagPropertyFromBrowse(String query, String scriptTag, NoThreadGeneralWebBot webBot){
	   String tagProperty = webBot.getScriptTagContentsFromPredictiveSearch(query, true, scriptTag, CATEGORY_IN_LANGUAGE);
	   Assert.assertTrue(String.format("search_browse was: {%s} but should have been: {%s}", tagProperty, configTag), tagProperty.equalsIgnoreCase("BROWSE"));
   }
	

}
