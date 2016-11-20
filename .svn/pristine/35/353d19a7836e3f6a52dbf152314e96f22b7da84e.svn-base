package com.rs_search_relevancy.test_search.quepid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class TestQuepidInvalidVariables {
	
	private String searchTerm;
	private String locale;
	private String env;
	
	private ArrayList<String> validEnvironments = TestQuepidInvalidVariableData.getListOfValidEnvironments();
	private ArrayList<String> validLocales = TestQuepidInvalidVariableData.getListOfValidLocales();
	
    private QupidBotFactory qupidBotFactory= new QupidBotFactory();
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    private ScrapperFactory scrapperFactory;

    @Before
    public void initialize() {
        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
    }

    public TestQuepidInvalidVariables(String searchTerm, String locale, String env) {
		this.searchTerm = searchTerm;
		this.locale = locale;
		this.env = env;
	}
    
    /**
     *format{ "<search Term>", "<locale>", "<env>", <cascade value>, <logic value>, <expected cascade result>, <expected logic result>},
     * due to the responses return by Quepid Http Service at current stage in time (14/04/16) it is not possible for both the logic and cascade configurations to be false. 
     * when this happens, quepid will return 'Invalid Logic Configurations' and should therefore be asserted as 'true, false'.
     * @return
     */
	@Parameterized.Parameters(name="Quepid Cascade and Logic Test = Query: {0}, Locale: {1},  Env: {2}")
    public static Collection<Object[]> createTestData(){
        return Arrays.asList(new Object[][] {
//          { "Brother", "uk", "sppsp"},
//          { "999 999", "uk", "sppsp"},
//          { "3mm LED", "ukk", "sssp"},
//          { "Brother Printer", "uk", "ppssp"},
//          { "Bav99", "ukk", "ssp"},
//          { "Printer", "uk", "sspppp"},
          { "543210", "uk", "st1"},
          
        });
    }
    
    @Test
    public void getCascadeResults() {	
    	System.out.println("\n------------------RUNNING TEST " + searchTerm + "---------------------------------------\n");
        NoThreadCupidBot qupidBot = qupidBotFactory.createNonThreadCupidBot();
        CupidHttpResult result = qupidBot.getResults(searchTerm, locale, env);
        System.out.println(result.getTotalNumberOfCategories());
        testVariables(result);
    }
    
    private void testVariables(CupidHttpResult result){
		System.out.println("Environment " + env + " was used!");
		System.out.println("Locale " + locale + " was used!");
    	if (validEnvironments.contains(env) && !validLocales.contains(locale)){
    		assertFalse(String.format("Locale Variable used: {%s} -  expected False returned True", result.isValidLocaleVariable()), result.isValidLocaleVariable());
    		assertTrue(String.format("Environment Variable used: {%s} -  expected True returned False", result.isValidEnvironmentVariable()), result.isValidEnvironmentVariable());
    	}else if (!validEnvironments.contains(env) && validLocales.contains(locale)){
    		assertTrue(String.format("Locale Variable used: {%s} -  expected True returned False", result.isValidLocaleVariable()), result.isValidLocaleVariable());
    		assertFalse(String.format("Environment Variable used: {%s} -  expected False returned True", result.isValidEnvironmentVariable()), result.isValidEnvironmentVariable());
    	}else if (!validEnvironments.contains(env) && !validLocales.contains(locale)){
    		assertFalse(String.format("Locale Variable used: {%s} -  expected False returned True", result.isValidLocaleVariable()), result.isValidLocaleVariable());
    		assertFalse(String.format("Environment Variable used: {%s} -  expected False returned True", result.isValidEnvironmentVariable()), result.isValidEnvironmentVariable());
    	}else{
    		assertTrue(String.format("Locale Variable used: {%s} -  expected True returned False", result.isValidLocaleVariable()), result.isValidLocaleVariable());
    		assertTrue(String.format("Environment Variable used: {%s} -  expected True returned False", result.isValidEnvironmentVariable()), result.isValidEnvironmentVariable());	
    	}	
    }
}