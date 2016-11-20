package com.rs_search_relevancy.test_search.quepid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.containers.pojo.CupidItem;
import com.ec.quepid.bot.CupidHttpResult;
import com.ec.quepid.bot.NoThreadCupidBot;
import com.ec.quepid.bot.QupidBotFactory;
import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;

@RunWith(Parameterized.class)
public class TestPhrasingOnTaxonomy {
	
	private final String LOCALE = "uk";
	private String searchTerm; 
	private final String ENV = "st1";
	
	private QupidBotFactory quepidBotFac = new QupidBotFactory(); 
	private CrawlerPropertiesReader crawlerProperties = new CrawlerPropertiesReader();
	private EnvPropertiesReader envProperties = new EnvPropertiesReader();
	
	public TestPhrasingOnTaxonomy(String searchTerm){
		this.searchTerm = searchTerm;
	}
	
	@Parameterized.Parameters(name="Test phrasing on taxonomy when searching for {0}")
	public static Collection<Object[]> CreateTestData(){ 
		return Arrays.asList(new Object[][] {
				{"coaxial cables"},
				{"torch bulbs"},
				{"battery testers"},
				{"dome nuts"},
				{"epoxy guns"},
				{"fax machines"},
				{"grabber clips"},
				{"hand wheels"},
				{"ir leds"},
				{"joystick boots"},
				{"knee pads"},
				{"label printers"}, 
				{"mini mills"},
				{"aa rechargeable batterys"}
				
		});
	}
	
	@Test
	public void getResultsFromSearch(){
		System.out.println("\n------------------RUNNING TEST " + searchTerm + "---------------------------------------\n");
    	List<Integer> listOfResults = new ArrayList<Integer>();
    	List<CupidItem> badResults = new ArrayList<CupidItem>();
    	List<CupidItem> goodResults = new ArrayList<CupidItem>();
    	NoThreadCupidBot quepidBot = quepidBotFac.createNonThreadCupidBot();
    	CupidHttpResult Result = quepidBot.getResults(searchTerm, LOCALE, ENV);
    	int x = 0;
    	for(CupidItem item : Result.getItems()){
    		x++;
    		String searchTermSingular = searchTerm.substring(0, searchTerm.length() - 1);
//    		item.prettyPrint();
    		if(item.getP_longDescription().toLowerCase().contains(searchTermSingular) || item.getP_primaryKeyword().toLowerCase().contains(searchTermSingular)){
    			goodResults.add(item);
    		}else{
    			badResults.add(item);
    			System.out.println(" result number " + x + " - " + item.getQuepidId() + " - " + item.getP_longDescription());
    		}
    	}
    	System.out.println("good : " + goodResults.size());
    	System.out.println("bad : " + badResults.size());
	}
	

}
