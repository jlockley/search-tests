/**
 * 
 */
package com.ec.datastubs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;
/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 23 Mar 2016 at 15:42:36
 *
 * ************************************************************************************************
 * </pre>
 */

public class TestDataStub {
    
    
    public TestDataStub(){
        
    }
    
    public Queue<TestCase> getTestCases(){
        
        Queue<TestCase> testcases = new ConcurrentLinkedQueue<TestCase>();
        
        
        Query t1q1 = new Query("12 Volt Battery");
        Query t1q2 = new Query("12 V battery");
        Query t1q3 = new Query("12V battery");
        
        TestCase testcase1 = new TestCase("Voltage synonyms",
                new ArrayList<String>(Arrays.asList("comparisons", "individual")), 
                new ArrayList<Query>(Arrays.asList(t1q1, t1q2, t1q3)),
                "st2", 
                "uk");
        
        
        Query t2q1 = new Query("12 amp fuse");
        Query t2q2 = new Query("12A fuse");
        Query t2q3 = new Query("12 A fuse");
        
        TestCase testcase2 = new TestCase("Amp synonyms",
                new ArrayList<String>(Arrays.asList("comparisons", "individual")), 
                new ArrayList<Query>(Arrays.asList(t2q1, t2q2, t2q3)),
                "st2", 
                "uk");
        
        Query t3q1 = new Query("red LED");
        TestCase testcase3 = new TestCase("Red Led logic check",
                new ArrayList<String>(Arrays.asList("individual")), 
                new ArrayList<Query>(Arrays.asList(t3q1)),
                "st2", 
                "uk");
        
        testcases.add(testcase1);
        testcases.add(testcase2);
        testcases.add(testcase3);
        
        return testcases;
        
        
    }
}
