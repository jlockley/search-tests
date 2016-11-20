/**
 * 
 */
package com.rs_search_relevancy.test_search.attribute;


import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.containers.pojo.EndecaItem;
import com.ec.endeca.EndecaConnection;
import com.ec.endeca.EndecaResult;
import com.ec.endeca.EndecaResultsGetter;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;


/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : 
 * Created : 4 May 2016 at 09:33:01
 *
 * ************************************************************************************************
 * </pre>
 */
@RunWith(Parameterized.class)
public class TestRangeAttributes {
    
    private String searchInterface;
    private String UoM;
    private String maxValue;
    private String minValue;
    private String wildCard;
    
    private String opts = "mode matchall";
  


    @Parameterized.Parameters(name="Range Attribute test: {1} to {2} unit {3} for interface {0}")
    public static Collection<Object[]> createTestData(){
        return Arrays.asList(new Object[][] {
            { "I18NsearchBySimilar", "0.54", "0.63", "A", "N"},
        });
    }

   
    public TestRangeAttributes(String searchInterface, String minValue, String maxValue, String UoM, String wildCard) {
        this.searchInterface = searchInterface;
        this.UoM = UoM;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.wildCard = wildCard;
    }

    /**
     * <h1> runOhmsTest </h1>
     * Run tests to assert that range value attributes are returned 
     * <h2> Logic </h2>
     * Instantiates Two Strings
     * <ul> 
     * <li> one containing the first value (min Value) + the unit of measure
     * <li> the other containing the second (max value) + the unit of measure
     * </ul>
     * Runs two endeca queries against specified interface - saves those to a local results variable 
     * (surrounded by a try catch to handle a potential error if the search params are wrong)
     * <p>
     * Calls assertRangeValueAttribute method with result variables and the max and min values as arguments
     */
    @Test
    public void runOhmsTest(){
        
        EndecaResultsGetter getItemsHelper = new EndecaResultsGetter();
        HttpENEConnection endecaConn = EndecaConnection.STATIC2UK.getConnection();
        EndecaResult minSearchResult = null;
        EndecaResult maxSearchResult = null;
        
        String searchTermMax = maxValue + UoM;
        String searchTermMin = minValue + UoM;
        
        try {
            maxSearchResult = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, searchTermMax, opts, wildCard);
            minSearchResult = getItemsHelper.runEndecaSearch(searchInterface, endecaConn, searchTermMin, opts, wildCard);
        } catch (ENEQueryException e) {
            e.printStackTrace();
        }
        assertRangeValueAttribute(maxSearchResult, maxValue);
        assertRangeValueAttribute(minSearchResult, minValue);
 
    }
    
    
    /**
     * <h1> assertRangeValueAttribute </h1>
     * Asserts that the Long Desc of a product contains range values
     * <h2> Logic </h2>
     * <p>
     * <b>Assert</b> result size is not 0 (actually contains results)
     * <b>for</b> every item within the results 
     * <ul><li>The item's Long description is stored 
     *  <li><b>If</b> the long description contains an arrow 
     * 		<ul>
     *		<li>Split the long description on the arrow
     *		<li><b>If</b> the left or right side of that split contains the searchValue
     *			<ul>
     *			<li>found is set to true
     *			<li>Assert that found is true
     *			</ul>
     *		</ul>
     * </ul>
     * 
     * <p>
     * If the item's long description doesn't contain a range value at all, it will fail
     * <p>
     * If the item's long description contains a range value but the searchValue is not either side of the arrow, it will fail
     * 
     * @param result The result variable should be the result of search against endeca (EndecaResult)
     * @param searchValue The searchValue will be the value from the search, without the unit of measure
     */
    private void assertRangeValueAttribute(EndecaResult result, String searchValue){
        Assert.assertFalse(String.format("No items returned for search result {%s}", result.getOriginalSearchTerm()),result.getEndecaItems().size() == 0);
        for (EndecaItem item : result.getEndecaItems()){
            String itemLongDesc = item.getLongDesc();
            if(itemLongDesc.contains("→")){
                boolean found = false;
                String[] parts = itemLongDesc.split("→");
                if(parts[0].contains(searchValue.trim()) || parts[1].contains(searchValue.trim())){
                    found = true;
                }
                Assert.assertTrue(String.format("item {%s} does not contain Value {%s}", parts), found);
            }
        }        
    }

}
