/**
 * 
 */
package com.ec.containers.test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.annotation.ThreadSafe;

import com.ec.datastubs.TestDataStub;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 18 Mar 2016 at 13:28:48
 *
 * ************************************************************************************************
 * </pre>
 */

@ThreadSafe
public class TestCasesContainer {

    private Queue<TestCase> testcases; 
     
    public TestCasesContainer(Queue testData){
        testcases = new TestDataStub().getTestCases();;
    }
    
    public TestCasesContainer(){
        testcases = new ConcurrentLinkedQueue<TestCase>();
    }
    
    public TestCase getTestFromQueue(){
        return testcases.poll();
    }    
    
    public void putTestInQueue(TestCase testCase){
        testcases.add(testCase);
    }
    
}
