/**
 * 
 */
package com.ec.analytics;

import com.ec.analytics.checks.results.NumberOfProductsResults;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 30 Mar 2016 at 14:54:49
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsTestResult {

    private int testId;
    private String name;
    private String redirectResult = null;
    private NumberOfProductsResults numberOfProductsResult = null;
    private String nameResult = null;
    private String attributeResult = null;
    
    AnalyticsTestResult(int TestId, String name){
        this.testId = TestId;
        this.setName(name);
    }

    
    /**
     * @return the testId
     */
    public int getTestId() {
        return testId;
    }
    
    /**
     * @return the redirectResult
     */
    public String getRedirectResult() {
        return redirectResult;
    }

    /**
     * @param redirectResult the redirectResult to set
     */
    public void setRedirectResult(String redirectResult) {
        this.redirectResult = redirectResult;
    }

    /**
     * @return the quantityResult
     */
    public NumberOfProductsResults getNumberOfProductsResult() {
        return numberOfProductsResult;
    }

    /**
     * @param quantityResult the quantityResult to set
     */
    public void setNumberOfProductsResult(NumberOfProductsResults numberOfProductsResult) {
        this.numberOfProductsResult = numberOfProductsResult;
    }

    /**
     * @return the nameResult
     */
    public String getNameResult() {
        return nameResult;
    }

    /**
     * @param nameResult the nameResult to set
     */
    public void setNameResult(String nameResult) {
        this.nameResult = nameResult;
    }

    /**
     * @return the attributeResult
     */
    public String getAttributeResult() {
        return attributeResult;
    }

    /**
     * @param attributeResult the attributeResult to set
     */
    public void setAttributeResult(String attributeResult) {
        this.attributeResult = attributeResult;
    }
    
    /**
     * 
     * @param testCase
     * @param fieldToUpdate
     */
    public void updateTestResult(TestCase testCase, String fieldToUpdate){
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
