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
 * Created : 30 Mar 2016 at 14:37:57
 *
 * ************************************************************************************************
 * </pre>
 */
public class RedirectCheck extends BaseCheck{

    private static final Logger LOGGER = LogManager.getLogger(RedirectCheck.class);
    
    @Override
    public void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        LOGGER.info("Running Redirect checker for Test: [{}]",testCase.getName());
        updateResult(testCase, testResultContainer);
    }

    private void updateResult(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        AnalyticsTestResult testResult = testResultContainer.getTestResult(testCase);
        synchronized (testResult) {    
            testResult.setRedirectResult("RedirectResult" + testCase.getName());
        }
    }

}
