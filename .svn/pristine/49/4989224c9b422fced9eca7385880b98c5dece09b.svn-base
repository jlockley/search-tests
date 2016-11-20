/**
 * 
 */
package com.ec.analytics.checks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.analytics.AnalyticsResultsContainer;
import com.ec.analytics.AnalyticsTestResult;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 13:56:34
 *
 * ************************************************************************************************
 * </pre>
 */
public class AttributesCheck extends BaseCheck{
    
    private static final Logger LOGGER = LogManager.getLogger(AttributesCheck.class);

    @Override
    public void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        LOGGER.info("Running Attribute checker for Test: [{}]",testCase.getName());
        updateResult(testCase, testResultContainer);
    }
    
    protected void updateResult(TestCase testCase, AnalyticsResultsContainer testResultContainer){

        AnalyticsTestResult testResult = testResultContainer.getTestResult(testCase);
        synchronized (testResult) {          
            testResult.setAttributeResult("AttributeTest"  + testCase.getName());
        }
    }


}
