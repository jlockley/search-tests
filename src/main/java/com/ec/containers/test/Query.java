/**
 * 
 */
package com.ec.containers.test;

import com.ec.quepid.bot.CupidHttpResult;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 24 Mar 2016 at 10:25:35
 *
 * ************************************************************************************************
 * </pre>
 */
public class Query {
    
    private String query;
    private CupidHttpResult CupidResult;
    
    public Query(String query){
        this.query = query;
    }
    
    /**
     * 
     * @return List of POJO cupid items
     */
    public CupidHttpResult getCupidQueryResult(){
        return CupidResult;
    }
    
    public void setCupidHttpResult(CupidHttpResult CupidResult){
        this.CupidResult = CupidResult;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

}
