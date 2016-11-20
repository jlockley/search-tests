/**
 * 
 */
package com.ec.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 30 Mar 2016 at 14:36:16
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsResultsContainer {
    
    private List<AnalyticsTestResult> analyticsResultsContainer;
    private static final Logger LOGGER = LogManager.getLogger(AnalyticsWaitingRoomContainer.class);
    
    public AnalyticsResultsContainer(){
        this.analyticsResultsContainer = Collections.synchronizedList(new ArrayList<AnalyticsTestResult>());
    }
    
    
    private int checkIfTestResultAlreadyExists(TestCase toAddTestCase){
        synchronized(analyticsResultsContainer){
            for(AnalyticsTestResult testResult : analyticsResultsContainer){
                if (testResult.getTestId() == toAddTestCase.getId()) {
                    return analyticsResultsContainer.indexOf(testResult);
                }
            }
        }
        return -1;
    }
    
    public AnalyticsTestResult getTestResult(TestCase toAddTestCase){
        int indexOfAssosiatedResult = checkIfTestResultAlreadyExists(toAddTestCase);
        if(indexOfAssosiatedResult == -1){
            AnalyticsTestResult testResult = new AnalyticsTestResult(toAddTestCase.getId(), toAddTestCase.getName());
            analyticsResultsContainer.add(testResult);
            return testResult;
        }else{
            AnalyticsTestResult testResult = analyticsResultsContainer.get(indexOfAssosiatedResult);
            return testResult;
        }
    }


}
