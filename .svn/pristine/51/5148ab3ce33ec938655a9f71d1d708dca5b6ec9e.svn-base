/**
 * 
 */
package com.ec.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.pojo.CupidItem;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 24 Mar 2016 at 13:42:35
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsWaitingRoomContainer {
    
    private List<TestCase> analyticsStartContainer;
    private static final Logger LOGGER = LogManager.getLogger(AnalyticsWaitingRoomContainer.class);
    
    public AnalyticsWaitingRoomContainer(){
        this.analyticsStartContainer = Collections.synchronizedList(new ArrayList<TestCase>());
    }

    /**
     * @return the analyticsStartContainer
     */
    public List<TestCase> getAnalyticsStartContainer() {
        return analyticsStartContainer;
    }
    
    public TestCase getTestCase() throws IndexOutOfBoundsException{
        try{
            return analyticsStartContainer.remove(0);
        }catch(IndexOutOfBoundsException e){
            LOGGER.info("No test cases in the list");
            throw e;
        }
    }

    /**
     * @param analyticsStartContainer the analyticsStartContainer to set
     */
    public void putTestInQueue(TestCase testCase) {
        analyticsStartContainer.add(testCase);
    }
    
    public ArrayList<CupidItem> iterableVersionOfList(){
        synchronized(analyticsStartContainer){
            return (ArrayList<CupidItem>) ((ArrayList<TestCase>) analyticsStartContainer).clone();
        }
    }
}
