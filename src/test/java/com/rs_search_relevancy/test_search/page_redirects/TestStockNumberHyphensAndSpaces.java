package com.rs_search_relevancy.test_search.page_redirects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.containers.pojo.EndecaItem;
import com.ec.endeca.EndecaConnection;
import com.ec.endeca.EndecaHelpers;
import com.ec.endeca.EndecaResult;
import com.ec.endeca.EndecaResultsGetter;
import com.ec.utils.TestConfigReader;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;

@RunWith(Parameterized.class)
public class TestStockNumberHyphensAndSpaces {
	
    private String searchInterface;
    private String searchTerm;
    private String wildCard;
    private boolean resultsReturned;
    
    private List<EndecaItem> unWantedResults = new ArrayList<EndecaItem>();
    
    private TestConfigReader testConfigReader = new TestConfigReader();
    
    public TestStockNumberHyphensAndSpaces(String searchInterface, String searchTerm, boolean resultsReturned, String wildCard){
    	this.searchInterface = searchInterface;
    	this.searchTerm = searchTerm; 
    	this.resultsReturned = resultsReturned;
    	this.wildCard = wildCard;
    }
    
    @Parameterized.Parameters(name="{1} Stock number predictive search. Interface: {0}")
    public static Collection<Object[]> createTestData(){ 
        return Arrays.asList(new Object[][] {
            { "I18NsearchByStockNumber", "LEUVA66B", false, "BOTH"},
            { "I18NsearchByStockNumber", "843", true, "BOTH"},
            { "I18NsearchByStockNumber", "123", true, "BOTH"},
            { "I18NsearchByStockNumber", "728", true, "BOTH"},
            { "I18NsearchByStockNumber", "rs 688-7204", true, "BOTH"},
            { "I18NsearchByStockNumber", "rs 688", true, "BOTH"},
            { "I18NsearchByStockNumber", "698-9886", true, "BOTH"},
            { "I18NsearchByStockNumber", "656-1365", true, "BOTH"},
            { "I18NsearchByStockNumber", "776-0939", true, "BOTH"},
            { "I18NsearchByStockNumber", "799-0593", true, "BOTH"},
            { "I18NsearchByStockNumber", "451-6739", true, "BOTH"},
            { "I18NsearchByStockNumber", "526-0487", true, "BOTH"},
            { "I18NsearchByStockNumber", "0599-235", true, "BOTH"},
            { "I18NsearchByStockNumber", "698- 9886", true, "NONE"},
            { "I18NsearchByStockNumber", "656 -1365", true, "NONE"},
            { "I18NsearchByStockNumber", "THIS WILL FAIL", true, "NONE"},
//            { "I18NsearchByStockNumber", "776 - 0939", true, "NONE"},
            { "I18NsearchByStockNumber", "rs 799 -0593", true, "NONE"},
            { "I18NsearchByStockNumber", "rs 451- 6739", true, "NONE"},
            { "I18NsearchByStockNumber", "0-5260487", false, "NONE"},
            { "I18NsearchByStockNumber", "05-99235", false, "NONE"},
            { "I18NsearchByStockNumber", "052-60487", false, "NONE"},
            { "I18NsearchByStockNumber", "0599-235", false, "NONE"},
            { "I18NsearchByStockNumber", "052604-87", false, "NONE"},
            { "I18NsearchByStockNumber", "059923-5", false, "NONE"}, 
        });
    }
    
    @Test
    public void predictiveStockNumberTest(){
        EndecaResultsGetter getItemsHelper = new EndecaResultsGetter();
        EndecaConnection CONN = testConfigReader.getEndecaConnGivenTestConfig();
        HttpENEConnection endecaConn = CONN.getConnection();
        EndecaResult result = null;
        String opts = "mode matchall";
        
        try {
           result = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, searchTerm, opts, wildCard);
        } catch (ENEQueryException e) {
            e.printStackTrace();
        }
        assertResultsContainQuery(result);       
    }
    
    private void assertResultsContainQuery(EndecaResult result){
    	List<EndecaItem> resultItems = result.getEndecaItems();
    	if (resultItems.size() == 0){
    		Assert.assertTrue("0 Results returned", !resultsReturned);
    	}else if(resultItems.size() >= 1) {
//    		int x = 0;
    		for(EndecaItem item : resultItems){
//    			x++;
    			if (!item.getGroupNbr().contains(searchTerm.replaceAll("((rs)|[ ]|[-] *?)", ""))){
//    				System.out.println(item.getGroupNbr() + " result number: " + x);
    				unWantedResults.add(item);
    			}	
    		}
    		System.out.println("Printed unWanted results for query: " + searchTerm + " -------------------------");
        	EndecaHelpers.printListedResults(unWantedResults);
    		int unWantedListSize = unWantedResults.size();
    		Assert.assertTrue("Expected 0 unwanted results and got: " + unWantedListSize, unWantedListSize == 0);
    	}
    }
    
}


