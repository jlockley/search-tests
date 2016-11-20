/**
 * 
 */
package com.ec.analytics.checks;

import com.ec.analytics.AnalyticsResultsContainer;
import com.ec.containers.test.TestCase;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 14:42:41
 *
 * ************************************************************************************************
 * </pre>
 */
public abstract class BaseCheck {
    
    public abstract void runChecker(TestCase testCase, AnalyticsResultsContainer testResultContainer);

}

