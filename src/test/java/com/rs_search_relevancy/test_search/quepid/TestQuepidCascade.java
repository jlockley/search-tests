package com.rs_search_relevancy.test_search.quepid;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.quepid.bot.CupidHttpResult;
import com.ec.quepid.bot.NoThreadCupidBot;
import com.ec.quepid.bot.QupidBotFactory;
import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.scrappers.ScrapperFactory;


@RunWith(Parameterized.class)
public class TestQuepidCascade {
	
	private String searchTerm;
	private String locale;
	private String env;
	private Integer maxCascades;
	private String maxLogic;
	
    private QupidBotFactory qupidBotFactory= new QupidBotFactory();
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();

    public TestQuepidCascade(String searchTerm, String locale, String env,
			Integer maxCascades, String maxLogic) {
		this.searchTerm = searchTerm;
		this.locale = locale;
		this.env = env;
		this.maxCascades = maxCascades;
		this.maxLogic = maxLogic;
	}

	@Parameterized.Parameters(name="Quepid Cascade and Logic Test = Query: {0}, Locale:{1},  Env:{2}, Max cascade:{3}, Max logic:{4} ")
    public static Collection<Object[]> createTestData(){
        return Arrays.asList(new Object[][] {
          { "Brother", "uk", "ssp", 5, "0"},
          { "999 999", "uk", "ssp", 5, "0"},
          { "3mm LED", "uk", "ssp", 5, "0"},
          { "Brother Printer", "uk", "ssp", 5, "0"},
          { "Bav99", "uk", "ssp", 5, "0"},
          { "Printer", "uk", "ssp", 5, "0"},
          { "543210", "uk", "ssp", 5, "0"},
          
          { "Brother", "uk", "st1", 5, "0"},
          { "999 999", "uk", "st1", 5, "0"},
          { "3mm LED", "uk", "st1", 5, "0"},
          { "Brother Printer", "uk", "st1", 5, "0"},
          { "Bav99", "uk", "st1", 5, "0"},
          { "Printer", "uk", "st1", 5, "0"},
          { "543210", "uk", "st1", 5, "0"},
        });
    }
    
    @Test
    public void getCascadeResults() {
    	
    	System.out.println("\n------------------RUNNING TEST " + searchTerm + "---------------------------------------\n");
    	ArrayList<Integer> listOfResults = new ArrayList<Integer>(); 
    	
        NoThreadCupidBot qupidBot = qupidBotFactory.createNonThreadCupidBot();
        double prodCount = 0;
        for (Integer cascade = 0; cascade <= maxCascades; cascade++) {
        	CupidHttpResult result = qupidBot.getResults(searchTerm, locale, env, cascade.toString(), maxLogic);
        	listOfResults.add(result.getTotalNumberOfProducts());
        	System.out.println("cascade number " + cascade +  " : results " + result.getTotalNumberOfProducts());
        	prodCount += result.getTotalNumberOfProducts();
        }
        
        double testResultsAverage = prodCount / maxCascades; 
        System.out.println(testResultsAverage);
        assertFalse("Average results were not as expected", listOfResults.contains(testResultsAverage) || testResultsAverage == (float)0);
    	//System.out.println("\n-------------------------------END TEST------------------------------------------\n");
    }
    
}
     