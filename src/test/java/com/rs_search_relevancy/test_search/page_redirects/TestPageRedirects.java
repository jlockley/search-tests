package com.rs_search_relevancy.test_search.page_redirects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.SearchContext;

import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;
import com.ec.quepid.bot.QupidBotFactory;
import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.scrappers.ScrapperFactory;

@RunWith(Parameterized.class)
public class TestPageRedirects{
//	
//    private String searchTerm;
//    private String expectedPage;
//    private String locale; 
//    
//    private QupidBotFactory qupidBotFactory= new QupidBotFactory();
//    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
//    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
//    private ScrapperFactory scrapperFactory;
//    
//    private final static String ENV = "st1";
//    
//    @Before
//    public void initialize() {
//        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
//    }
//    
//    public TestPageRedirects(String searchTerm, String expectedPage, String locale){
//    	this.searchTerm = searchTerm;
//    	this.expectedPage = expectedPage;
//    	this.locale = locale;
//    }
//    
//    @Parameterized.Parameters(name="Query: {0}, Locale: {1}, Expected Redirect: {2}")
//    public static Collection<Object[]> createTestData() {
//    	
//    }
//    
//    private TestCase buildTestCase(){
//        List<String> typeSearch = new ArrayList<>();
//        List<Query> queryList = new ArrayList<Query>();
//    	TestCase newTestCase = new TestCase(String.format("page redirect for query : {%s}", searchTerm), typeSearch,queryList,ENV, locale);
//        return newTestCase;
//    }
//    
//    @After
//    public void deleteQuery(){
//    	TestUKPageRedirectsData.getSuperSectionQueries().remove(searchTerm);
//    }

}
