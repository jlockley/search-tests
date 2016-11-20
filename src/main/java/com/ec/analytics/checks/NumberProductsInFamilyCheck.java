/**
 * 
 */
package com.ec.analytics.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
 * Created : 5 Apr 2016 at 16:06:53
 *
 * ************************************************************************************************
 * </pre>
 */
public class NumberProductsInFamilyCheck extends BaseCheck{

    private static final Logger LOGGER = LogManager.getLogger(NumberOfProductsCheck.class);

    @Override
    public void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer) {
        AnalyticsTestResult testResult = testResultContainer.getTestResult(testCase);
        HashMap<String, Map<String, Integer>> queryToFamilyCount = new HashMap<String, Map<String, Integer>>();
        synchronized (testResult) {    
            for(Query query : testCase.getQuerys()){
                ArrayList<CupidItem> queryItems= query.getCupidQueryResult().getItems();
                Map<String, Integer> groupedByFamilyCount = 
                queryItems.stream()
                           .collect(Collectors.groupingBy(CupidItem::getP_brand,
                                                          Collectors.summingInt(CupidItem::addOne)));
                queryToFamilyCount.put(query.getQuery(), groupedByFamilyCount);
            }
        }
        updateResult(testCase, testResultContainer, queryToFamilyCount);
    }

    private void updateResult(TestCase testCase, AnalyticsResultsContainer testResultContainer, HashMap<String, Map<String, Integer>> queryGroupedByFamily) {
        LOGGER.info(queryGroupedByFamily);
    }

}
