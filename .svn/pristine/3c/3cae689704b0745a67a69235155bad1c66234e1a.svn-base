package com.rs_search_relevancy.test_search.quepid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class TestQuepidInvalidConfig {
	
	private String searchTerm;
	private String locale;
	private String env;
	private Integer cascade;
	private Integer logic;
	private boolean validSearchConfig;
	private boolean validLogicConfig;
	
    private QupidBotFactory qupidBotFactory= new QupidBotFactory();
    private CrawlerPropertiesReader crawlerPropertiesReader = new CrawlerPropertiesReader();
    private EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    private ScrapperFactory scrapperFactory;
    
    private final static String ENV = "st2";

    @Before
    public void initialize() {
        this.scrapperFactory = new ScrapperFactory(crawlerPropertiesReader, envPropertiesReader);
    }

    public TestQuepidInvalidConfig(String searchTerm, String locale, String env,
			Integer cascade, Integer logic, boolean validSearchConfig, boolean validLogicConfig) {
		this.searchTerm = searchTerm;
		this.locale = locale;
		this.env = env;
		this.cascade = cascade;
		this.logic = logic;
		this.validLogicConfig = validLogicConfig;
		this.validSearchConfig = validSearchConfig;
	}
    
    /**
     *format{ "<search Term>", "<locale>", "<env>", <cascade value>, <logic value>, <expected cascade result>, <expected logic result>},
     * due to the responses return by Quepid Http Service at current stage in time (14/04/16) it is not possible for both the logic and cascade configurations to be false. 
     * when this happens, quepid will return 'Invalid Logic Configurations' and should therefore be asserted as 'true, false'.
     * @return
     */
	@Parameterized.Parameters(name="Quepid Cascade and Logic Test = Query: {0}, Locale: {1},  Env: {2}, Max cascade: {3}, Max logic: {4}, Cascade Exists: {5}, Logic Exists: {6} ")
    public static Collection<Object[]> createTestData(){
        return Arrays.asList(new Object[][] {
          { "Brother", "uk", ENV, -2, 1, false, true},
          { "999 999", "uk", ENV, 1, 9, true, false},
          { "3mm LED", "uk", ENV, 8, 1, false, true},
          { "Brother Printer", "uk", ENV, 99, 6, true, false},
          { "Bav99", "uk", ENV, 1, 1, true, true},
          { "Printer", "uk", ENV, 5, 1, true, true},
          { "543210", "uk", ENV, 5, 4, true, false},
          
          { "Brother", "it", ENV, -2, 1, false, true},
          { "999 999", "f1", ENV, 1, 9, true, false},
          { "3mm LED", "de", ENV, 8, 1, false, true},
          { "Brother Printer", "it", ENV, 99, 6, true, false},
          { "Bav99", "sg", ENV, 10, 1, false, true},
          { "Printer", "es", ENV, 0, 1, false, true},
          { "543210", "cz", ENV, 5, 4, true, false},
          { "543210", "ph", ENV, 5, 4, true, false},
          
        });
    }
    
    @Test
    public void getCascadeResults() {
    	
    	System.out.println("\n------------------RUNNING TEST " + searchTerm + "---------------------------------------\n");
        NoThreadCupidBot qupidBot = qupidBotFactory.createNonThreadCupidBot();
        CupidHttpResult result = qupidBot.getResults(searchTerm, locale, env, cascade.toString(), logic.toString());
        
        System.out.println(result.isValidSearchLogic());
        System.out.println(result.isValidSearchConfig());
        
        if(validLogicConfig){
        	assertTrue(String.format("Valid Logic Config should be true but was {%b}", result.isValidSearchLogic()), result.isValidSearchLogic());
        }else{
        	assertFalse(String.format("Valid Logic Config should be false but was {%b}", result.isValidSearchLogic()), result.isValidSearchLogic());
        }
        if(validSearchConfig){
        	assertTrue(String.format("Valid Search Config should be true but was {%b}", result.isValidSearchConfig()), result.isValidSearchConfig());        	
        }else{
        	assertFalse(String.format("Valid Search Config should be false but was {%b}", result.isValidSearchConfig()), result.isValidSearchConfig());
        }
        
        
    }
        
    
}
     