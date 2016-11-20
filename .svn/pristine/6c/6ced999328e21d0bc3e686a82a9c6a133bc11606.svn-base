/**
 * 
 */
package com.ec.analytics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.analytics.checks.BaseCheck;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 14:00:57
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsChecker implements Runnable {

    private AnalyticsWaitingRoomContainer testCasesContainer;
    private AnalyticsResultsContainer analyticsResultsContainer;
    private static final Logger LOGGER = LogManager.getLogger(AnalyticsChecker.class);
    private volatile boolean runnable;
    private HashMap<String, List<BaseCheck>> testTypesDict;
    

    public AnalyticsChecker(AnalyticsWaitingRoomContainer testCasesContainer, AnalyticsResultsContainer analyticsResultsContainer, HashMap<String, List<BaseCheck>> testTypesDict) {
        this.testCasesContainer = testCasesContainer;
        this.runnable = true;
        this.testTypesDict = testTypesDict;
        this.analyticsResultsContainer = analyticsResultsContainer;
    }

    @Override
    public void run() {
        while (runnable){
            try {
                TestCase testCase = testCasesContainer.getTestCase();
                LOGGER.info("-----------------------------------------------------------------------------------------------");
                LOGGER.info("running analytics for test case [{}]", testCase.getName());
                runCheckers(testCase);
            } catch (IndexOutOfBoundsException e) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    LOGGER.info("Interupting analytics checks, BOOM");
                }
            }
        }
    }
    
    private void runCheckers(TestCase testCase){
        Set<BaseCheck> analyticsToRun = buildCheckersList(testCase);;
        for(BaseCheck checker : analyticsToRun){
            checker.runChecker(testCase, analyticsResultsContainer);
        }      
    }
        
    private Set<BaseCheck> buildCheckersList(TestCase testCase){
        List<BaseCheck> typesOfTest = new ArrayList<BaseCheck>();
        for (String type : testCase.getType()){
            List<BaseCheck> checkertypes = testTypesDict.get(type);
            typesOfTest.addAll(checkertypes);
        }

        Set<BaseCheck> uniqueTypes = new TreeSet<BaseCheck>(new Comparator<Object>() {

            @Override
            public int compare(Object o1, Object o2) {
                if(o1.getClass().getName().equals(o2.getClass().getName())){
                    return 0;
                }
                return 1;
            }
        });
        
        uniqueTypes.addAll(typesOfTest);
        return uniqueTypes;
    }
    
    

}
