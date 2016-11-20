/**
 * 
 */
package com.ec.analytics.checks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.analytics.AnalyticsResultsContainer;
import com.ec.analytics.AnalyticsTestResult;
import com.ec.containers.pojo.CupidItem;
import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 14:45:53
 *
 * ************************************************************************************************
 * </pre>
 */
public class NameCheck extends BaseCheck{

    private static final Logger LOGGER = LogManager.getLogger(NameCheck.class);
    
    @Override
    public void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        LOGGER.info("Running Name checker for Test: [{}]",testCase.getName());
        updateResult(testCase, testResultContainer);
        
    }

    
    //TODO AT MOMENT VERY BASIC CHECK. PLAN TO REPLACE USING NATURAL LANGUAGE PROCESSOR
    protected void updateResult(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        AnalyticsTestResult testResult = testResultContainer.getTestResult(testCase);
        synchronized (testResult) {    
            for(Query query : testCase.getQuerys())
                for(CupidItem item: query.getCupidQueryResult().getItems()){
                    //StringBuffer itemName = item.getAttributeNameValue_text();
                }
            testResult.setNameResult("Name result : " + testCase.getName());
        }
    }

}
