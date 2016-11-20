package com.rs_search_relevancy.test_search.similar_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ec.analytics.checks.generics.groupItemGenerics;
import com.ec.containers.pojo.EndecaItem;
import com.ec.endeca.EndecaConnection;
import com.ec.endeca.EndecaResult;
import com.ec.endeca.EndecaResultsGetter;
import com.ec.utils.TestConfigReader;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;
import com.rs_search_relevancy.test_search.result_containers.SimilarSearchResult;
import com.rs_search_relevancy.test_search.utils.Output;

@RunWith(Parameterized.class)
public class SimilarSearchRunner {
	

	private final static CategoryCount SIMILAR_SEARCH = new CategoryCount();
	
    private static TestConfigReader testConfigReader = new TestConfigReader();
    private static final EndecaConnection CONN = testConfigReader.getEndecaConnGivenTestConfig(); 
    
    private String searchInterface;
    private String searchTerm1;
    private String searchTerm2;
    
    private ArrayList<String> uniqueFamilyList = new ArrayList<String>();
    private Integer intNumMatchedFamilies;
    private static ArrayList<String> families;
   
    private TreeSet<EndecaItem> uniqueProductsOne = new TreeSet<EndecaItem>();
    private TreeSet<EndecaItem> uniqueProductsTwo = new TreeSet<EndecaItem>();

    public SimilarSearchRunner(String searchInterface, String searchTerm, String searchTerm2) throws Exception{
    	this.searchInterface = searchInterface;
    	this.searchTerm1 = searchTerm;
    	this.searchTerm2 = searchTerm2;
   }
    
    public static ArrayList<String> getFamilies(String searchTerm) {
    	families = SIMILAR_SEARCH.createFamilies("similar_search", searchTerm, "category_count_families");
    	return families;
    }
    
    @Parameters(name= "{1} VS {2}")
    public static List<Object[]> getTestData(){
    	return SIMILAR_SEARCH.createTestData("#", "similar_search", "category_count_data");
    }
    
    @Test
    public void runSimilarSearchTests(){
    	HttpENEConnection endecaConn = CONN.getConnection();
    	EndecaResultsGetter getItemsHelper = new EndecaResultsGetter();
    	
        EndecaResult result1 = SIMILAR_SEARCH.getResults(searchTerm1, endecaConn, getItemsHelper, searchInterface);
        EndecaResult result2 = SIMILAR_SEARCH.getResults(searchTerm2, endecaConn, getItemsHelper, searchInterface);
    	
    	Integer resultSize1 = SIMILAR_SEARCH.getResultSize(result1);
    	Integer resultSize2 = SIMILAR_SEARCH.getResultSize(result2);

    	Map<String, Integer> familyMapping1 = SIMILAR_SEARCH.getFamilyMappings(result1);
    	Map<String, Integer> familyMapping2 = SIMILAR_SEARCH.getFamilyMappings(result2);
    	
    	List<String> commonFamilies = getCommonFamilies(getFamilies(searchTerm1), familyMapping1, familyMapping2);
    	
    	SIMILAR_SEARCH.printMappings(searchTerm1, searchTerm2, familyMapping1, familyMapping2);
	    
    	getUniqueItems(result1, result2, commonFamilies, resultSize1, resultSize2);
		
    	outputResultObjects(resultSize1, resultSize2);

		//printUniqueItems();
		Assert.assertTrue(String.format("Expected 0 unique Items in Expected Families - One: {%s},  Two: {%s}", uniqueProductsOne.size(),uniqueProductsTwo.size()), uniqueProductsOne.size() == 0 || uniqueProductsTwo.size() == 0);
     }
    
    private void getUniqueItems(EndecaResult result1, EndecaResult result2, List<String> commonFamilies, Integer resultSize1, Integer resultSize2){
    	System.out.println(String.format("Total number of results: {%s}-{%s} {%s}-{%s} Total common families: {%s} ",searchTerm1, resultSize1, searchTerm2, resultSize2, intNumMatchedFamilies));
		for (String family : commonFamilies){
		 List<TreeSet<EndecaItem>> uniqueProducts = SimilarSearchTools.compareResults(family, result1, result2);
		 uniqueProductsOne.addAll(uniqueProducts.get(0));
		 uniqueProductsTwo.addAll(uniqueProducts.get(1));
		}
    }
    
    private List<String> getCommonFamilies(ArrayList<String> familyList, Map<String, Integer> familyMapping, Map<String, Integer> familyMapping2){
    	List<String> commonFamilyList = new ArrayList<String>();
    	for (String family : familyList){
			if (familyMapping.keySet().contains(family) && familyMapping2.keySet().contains(family)){
				commonFamilyList.add(family);
			}else if(!familyMapping.containsKey(family) && familyMapping2.containsKey(family)){
				uniqueFamilyList.add(family);
				System.out.println(family + " Is a unique family");
			}else if(!familyMapping2.containsKey(family) && familyMapping.containsKey(family)){
				uniqueFamilyList.add(family);
				System.out.println(family + " Is a unique family");
			}
		}
    	intNumMatchedFamilies =  commonFamilyList.size();
    	return commonFamilyList;
    }
    
    private void outputResultObjects(Integer resultSize1, Integer resultSize2){
    	Map<String, String> resultMap = new HashMap<String , String>();
    	resultMap.put("locale", testConfigReader.getTestConfigLocale());
    	resultMap.put("environment", testConfigReader.getTestConfigEnv());
    	resultMap.put("UniqueFamilies", uniqueFamilyList.toString());
    	resultMap.put("strNumOfMatchedFamilies", intNumMatchedFamilies.toString());
    	Integer familiesSize = families.size();
    	
    	resultMap.put("familiesSize", familiesSize.toString());
		
		SimilarSearchResult resultObj1 = new SimilarSearchResult(searchTerm1, resultSize1.toString(), resultMap.get("UniqueFamilies"), resultMap.get("strNumOfMatchedFamilies"), familiesSize.toString());
		SimilarSearchResult resultObj2 = new SimilarSearchResult(searchTerm2, resultSize2.toString(), resultMap.get("UniqueFamilies"), resultMap.get("strNumOfMatchedFamilies"), familiesSize.toString());
		
		Output.toSpreadsheet("similar_search", resultMap.get("environment"), resultMap.get("locale"), resultObj1, resultObj2);
    }
    

    

}
