/**
 * 
 */
package com.ec.analytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ec.analytics.checks.AttributesCheck;
import com.ec.analytics.checks.BaseCheck;
import com.ec.analytics.checks.NameCheck;
import com.ec.analytics.checks.NumberOfProductsCheck;
import com.ec.analytics.checks.NumberProductsInFamilyCheck;
import com.ec.analytics.checks.RedirectCheck;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 29 Mar 2016 at 14:42:19
 *
 * ************************************************************************************************
 * </pre>
 */
public class AnalyticsTypeDictionary {

    private HashMap<String, List<BaseCheck>> typeDictionary;

    public AnalyticsTypeDictionary() {
        this.typeDictionary = new HashMap<String, List<BaseCheck>>();
        typeDictionary.put("individual", new ArrayList<BaseCheck>(Arrays.asList(new NameCheck(), new AttributesCheck())));
        typeDictionary.put("comparisons", new ArrayList<BaseCheck>(Arrays.asList(new NameCheck(), new NumberProductsInFamilyCheck(), new NumberOfProductsCheck())));
        typeDictionary.put("redirect", new ArrayList<BaseCheck>(Arrays.asList(new RedirectCheck())));
    }
    
    public HashMap<String, List<BaseCheck>> getDictionary(){
        return typeDictionary;
    }

}
