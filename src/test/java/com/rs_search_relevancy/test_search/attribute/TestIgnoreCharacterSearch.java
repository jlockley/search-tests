/**
 * 
 */
package com.rs_search_relevancy.test_search.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.containers.pojo.EndecaItem;
import com.ec.endeca.EndecaConnection;
import com.ec.endeca.EndecaResult;
import com.ec.endeca.EndecaResultsGetter;
import com.ec.utils.TestConfigReader;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;
import com.rs_search_relevancy.test_search.similar_search.SimilarSearchTools;

/**
 * 
 * @desc: Checks number of terms with term after @ symbol is reduced compared to without the @
 *
 */
@RunWith(Parameterized.class)
public class TestIgnoreCharacterSearch {
    
    private String searchInterface;;
    private String WithAtSymbolLongQuery;
    private String firstQuery;
    private String secondQuery;
    private String thirdQuery;
    private String wildCard;
    
    private String opts = "mode matchall";
    
    private TestConfigReader testConfigReader = new TestConfigReader();

    @Parameterized.Parameters(name="ignore after @ symbol")
    public static Collection<Object[]> createTestData(){
        return Arrays.asList(new Object[][] {
            //{ "I18NsearchBySimilar", "8 A@ 30 Vdc", "8 A relay", "8A relay", "8Amp relay", "N"},
            { "I18NLDescTaxonomyBrandSearchTerm2", "8 A@ 30 V dc", "8 A relay", "8A relay", "8Amp relay", "N"},
            { "I18NLDescTaxonomyBrandSearchTerm2", "100 mA@ 5 V dc", "100mA relay", "100 mA relay", "100 milli amp relay", "B"},
            { "I18NLDescTaxonomyBrandSearchTerm2", "25 A@ 277 V ac", "25A Relay", "25 A Relay", "25 amp relay", "N"},
            
        });
    }

   
    public TestIgnoreCharacterSearch(String searchInterface, String searchTermWithAtSymbol, String noSpace, String withSpace, String wordSearch, String wildCard) {
        this.searchInterface = searchInterface;
        this.WithAtSymbolLongQuery = searchTermWithAtSymbol;
        this.firstQuery = noSpace;
        this.secondQuery = withSpace;
        this.thirdQuery = wordSearch;
        this.wildCard = wildCard;
    }

    @Test
    public void runIgnoreCharaterTests(){
     
        EndecaResultsGetter getItemsHelper = new EndecaResultsGetter();
        EndecaConnection CONN = testConfigReader.getEndecaConnGivenTestConfig();
        HttpENEConnection endecaConn = CONN.getConnection();
        EndecaResult resultWithAtSymbol = null;
        EndecaResult resultFirstSearch = null;
        EndecaResult resultSecondSearch = null;
        EndecaResult resultThirdSearch = null;
        
        try{
            resultWithAtSymbol = getItemsHelper.runEndecaSearch("I18NLDescTaxonomyBrandSearchTerm", endecaConn, WithAtSymbolLongQuery, opts, wildCard);
            resultFirstSearch = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, firstQuery, opts, wildCard);
            resultSecondSearch = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, secondQuery, opts, wildCard);
            resultThirdSearch = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, thirdQuery, opts, wildCard);
        }catch (ENEQueryException e) {
            e.printStackTrace();
        }
        
        List<EndecaItem> resultFilteredWithAtSymbol = fiterOutItemsWithPhraseInAttributes(resultWithAtSymbol.getEndecaItems(), WithAtSymbolLongQuery);       
        List<EndecaItem> itemsMissingFirstWord = SimilarSearchTools.checkItemsInList(resultFirstSearch.getEndecaItems(), resultFilteredWithAtSymbol);
        List<EndecaItem> itemsMissingSecondWord = SimilarSearchTools.checkItemsInList(resultSecondSearch.getEndecaItems(), resultFilteredWithAtSymbol);
        List<EndecaItem> itemsMissingThirdWord = SimilarSearchTools.checkItemsInList(resultThirdSearch.getEndecaItems(), resultFilteredWithAtSymbol);
        
      
       String resultFormating = "{%s} Number of items: {%s}, Number of items in {%s} search missing in {%s} : {%s} ";
       
       
       
       System.out.println();
       System.out.println("Number of item found with attribute containing {" + WithAtSymbolLongQuery + "} : {" + resultFilteredWithAtSymbol.size() + "} ---> using interface I18NLDescTaxonomyBrandSearchTerm");
       System.out.println(String.format("using interface {%s}", searchInterface));
       printResultsSummary(resultFirstSearch, itemsMissingFirstWord);
       printResultsSummary(resultSecondSearch, itemsMissingSecondWord);
       printResultsSummary(resultThirdSearch, itemsMissingThirdWord);
       printMissingResults(firstQuery, itemsMissingFirstWord);
       printMissingResults(secondQuery, itemsMissingSecondWord);
       printMissingResults(thirdQuery, itemsMissingThirdWord);
       
    }
    
    private void printResults(){
        
    }
    
    private void printResultsSummary(EndecaResult resultSearch, List<EndecaItem>itemMissing){
        String resultFormating = "{%s} Number of items: {%s}, Number of items in {%s} search missing in {%s} : {%s} ";
        System.out.println(String.format(resultFormating, resultSearch.getOriginalSearchTerm(), resultSearch.getEndecaItems().size(), WithAtSymbolLongQuery, firstQuery, itemMissing.size()));
        
    }
    
    private void printMissingResults(String searchQuery, List<EndecaItem> itemsMissing){
        System.out.println();
        System.out.println("missing items for " + searchQuery);
        for(EndecaItem item : itemsMissing){
            item.prettyPrint();
        }
    }
    
    private List<EndecaItem> fiterOutItemsWithPhraseInAttributes(List<EndecaItem> itemListToFilter, String phrase){
        List<EndecaItem> itemsWithPhraseInAttribute = new ArrayList<EndecaItem>();
        for (EndecaItem item : itemListToFilter){
            if(item.getAttributes()!= null){
                if(item.getAttributes().contains(phrase)){
                    itemsWithPhraseInAttribute.add(item);
                }
            }
        }
        return itemsWithPhraseInAttribute;
    }
}

