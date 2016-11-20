package com.rs_search_relevancy.test_search.similar_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.ec.analytics.checks.generics.groupItemGenerics;
import com.ec.endeca.EndecaResult;
import com.ec.endeca.EndecaResultsGetter;
import com.ec.utils.TestConfigReader;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;

public class CategoryCount {
	
	private static GetCategoryCountData data = new GetCategoryCountData();
	
    private static TestConfigReader testConfigReader = new TestConfigReader();
    
    private static final String LOCALE = testConfigReader.getTestConfigLocale();
    private static final String ENVIRONMENT = testConfigReader.getTestConfigEnv();
    
    public ArrayList<String> createFamilies(String testName, String searchTerm, String fileName) {
    	String category = searchTerm.substring(searchTerm.lastIndexOf(" ")+1);
    	String filePath = data.getFilePath(testName, LOCALE, ENVIRONMENT, category, fileName);
    	return data.parseCsvAsList(filePath, ",");
    }
    
    public List<Object[]> createTestData(String delimeter, String testName, String fileName){
    	String filePath = data.getFilePath(testName, LOCALE, ENVIRONMENT, "data", fileName);
    	List<Object[]> testData = data.parseCsv(filePath, delimeter);
    	return testData;
    }
     
    public EndecaResult getResults(String searchTerm, HttpENEConnection endecaConn, EndecaResultsGetter getItemsHelper, String searchInterface){
    	EndecaResult tempResult = null;  
    	try {
    		tempResult = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, searchTerm, "mode+matchpartialmax", "NONE");
         } catch (ENEQueryException e) {
             e.printStackTrace();
         }
    	return tempResult;
    }
    
    public Integer getResultSize(EndecaResult result){
    	Integer i = result.getEndecaItems().size();
    	Assert.assertTrue(String.format("Zero Reults Returned: {%s}", i), i != 0);
    	return i;
    }
    
    public Map<String, Integer> getFamilyMappings(EndecaResult result){
    	return groupItemGenerics.byFamilyCount(result.getEndecaItems());
    }
    
    public void printMappings(String searchTerm1, String searchTerm2, Map<String, Integer> familyMapping, Map<String, Integer> familyMapping2){
    	System.out.println("\n-------------------Family Mappings for search-----------------------");
		System.out.println("Query " + searchTerm1 + " : " + familyMapping);
		System.out.println("Query " + searchTerm2 + " : " + familyMapping2);
		System.out.println("----------------------------------------------------------------------\n");
    }

}
