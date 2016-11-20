/**
 * 
 */
package com.ec.analytics.checks;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.analytics.AnalyticsResultsContainer;
import com.ec.analytics.AnalyticsTestResult;
import com.ec.analytics.checks.results.NumberOfProductsResults;
import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 13:57:42
 *
 * ************************************************************************************************
 * </pre>
 */
public class NumberOfProductsCheck extends BaseCheck{

    private static final Logger LOGGER = LogManager.getLogger(NumberOfProductsCheck.class);
    
    @Override
    public void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        LOGGER.info("Running Quantity checker for Test: [{}]",testCase.getName());
        updateResult(testCase, testResultContainer);
    }
    
    private void updateResult(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        AnalyticsTestResult testResult = testResultContainer.getTestResult(testCase);
        synchronized (testResult) {    
            Float average = (float) 0;
            Integer total = 0;
            HashMap<String, Integer> queriesWithTotals = new HashMap<String, Integer>();
            for(Query query : testCase.getQuerys()){
                Integer totalItemsInQuery = query.getCupidQueryResult().getTotalNumberOfProducts();
                total+=totalItemsInQuery;
                queriesWithTotals.put(query.getQuery(), totalItemsInQuery);
            }
            average =  ((float)total)/testCase.getQuerys().size();
            NumberOfProductsResults result = new NumberOfProductsResults(total, average, queriesWithTotals);    
            testResult.setNumberOfProductsResult(result);        
        }
    }

}
