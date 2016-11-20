/**
 * 
 */
package com.ec.analytics.checks.results;

import java.util.HashMap;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 4 Apr 2016 at 15:08:46
 *
 * ************************************************************************************************
 * </pre>
 */
public class NumberOfProductsResults {
    
    private Integer totalNumberOfProducts;
    private Float averageNumberOfProducts;
    private HashMap<String, Integer> queryWithTotalProducts;
    
    /**
     * @param totalNumberOfProducts
     * @param averageNumberOfProducts
     */
    public NumberOfProductsResults(Integer totalNumberOfProducts, Float averageNumberOfProducts, HashMap<String, Integer> queryWithTotalProducts) {
        this.totalNumberOfProducts = totalNumberOfProducts;
        this.averageNumberOfProducts = averageNumberOfProducts;
        this.queryWithTotalProducts = queryWithTotalProducts;
    }
    
    
    /**
     * @return the queryWithTotalProducts
     */
    public HashMap<String, Integer> getQueryWithTotalProducts() {
        return queryWithTotalProducts;
    }


    /**
     * @param queryWithTotalProducts the queryWithTotalProducts to set
     */
    public void setQueryWithTotalProducts(HashMap<String, Integer> queryWithTotalProducts) {
        this.queryWithTotalProducts = queryWithTotalProducts;
    }


    /**
     * @return the totalNumberOfProducts
     */
    public Integer getTotalNumberOfProducts() {
        return totalNumberOfProducts;
    }

    /**
     * @param totalNumberOfProducts the totalNumberOfProducts to set
     */
    public void setTotalNumberOfProducts(Integer totalNumberOfProducts) {
        this.totalNumberOfProducts = totalNumberOfProducts;
    }
    
    /**
     * @return the averageNumberOfProducts
     */
    public Float getAverageNumberOfProducts() {
        return averageNumberOfProducts;
    }
    
    /**
     * @param averageNumberOfProducts the averageNumberOfProducts to set
     */
    public void setAverageNumberOfProducts(Float averageNumberOfProducts) {
        this.averageNumberOfProducts = averageNumberOfProducts;
    }
    


}
