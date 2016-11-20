/**
 * 
 */
package com.ec.analytics;

import java.util.HashMap;
import java.util.List;

import com.ec.analytics.checks.BaseCheck;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 13:55:53
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsFactory {
    
    public AnalyticsChecker createAnalyticsChecker(AnalyticsWaitingRoomContainer analyticsContainer, AnalyticsResultsContainer analyticsResultsContainer){
        HashMap<String, List<BaseCheck>> testDict = new AnalyticsTypeDictionary().getDictionary();
        return new AnalyticsChecker(analyticsContainer, analyticsResultsContainer, testDict);
    }
}
