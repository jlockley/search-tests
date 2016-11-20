/**
 * 
 */
package com.ec.endeca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.pojo.EndecaItem;
import com.endeca.navigation.ESearchAutoSuggestion;
import com.endeca.navigation.ESearchReport;
import com.endeca.navigation.HttpENEConnection;
import com.endeca.navigation.Navigation;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 11 Apr 2016 at 08:34:51
 *
 * ************************************************************************************************
 * </pre>
 */
public class EndecaHelpers {
    
    private static final Logger LOGGER = LogManager.getLogger(EndecaHelpers.class);
    private static final String L = "LEFT";
    private static final String R = "RIGHT";
    private static final String B = "BOTH";
    private static final String N = "NONE";
    
    public static HttpENEConnection getEndecaConnection() {
        String endecaHost = "ecllapjmdxs653.ebs.ecomp.com";
        String endecaPort = "18017";
        LOGGER.debug("host queried is " + endecaHost + " :::: port used is " + endecaPort);
        return new HttpENEConnection(endecaHost, endecaPort);
    }
    
    public static boolean checkForAutomaticPhrasing(Navigation nav){
        List eSearchAutoSuggestionsList = generalCorrectionSetup(nav);
        if (eSearchAutoSuggestionsList != null && !eSearchAutoSuggestionsList.isEmpty()) {
            ESearchAutoSuggestion eSearchAutoSuggestion = (ESearchAutoSuggestion) eSearchAutoSuggestionsList.get(0);
            boolean autoPhrasing = eSearchAutoSuggestion.didSuggestionIncludeAutomaticPhrasing();
            return autoPhrasing;
        }
        return false;
    }
    
    public static boolean checkFromSpellingCorrection(Navigation nav){
        List eSearchAutoSuggestionsList = generalCorrectionSetup(nav);
        if (eSearchAutoSuggestionsList != null && !eSearchAutoSuggestionsList.isEmpty()) {
            ESearchAutoSuggestion eSearchAutoSuggestion = (ESearchAutoSuggestion) eSearchAutoSuggestionsList.get(0);
            boolean autoPhrasing = eSearchAutoSuggestion.didSuggestionIncludeSpellingCorrection();
            return autoPhrasing;
        }
        return false;
    }
    
    public static String getActualSearchTerm(Navigation nav){
        List eSearchAutoSuggestionsList = generalCorrectionSetup(nav);
        if (eSearchAutoSuggestionsList != null && !eSearchAutoSuggestionsList.isEmpty()) {
            ESearchAutoSuggestion eSearchAutoSuggestion = (ESearchAutoSuggestion) eSearchAutoSuggestionsList.get(0);
            String actualSearchTerm = eSearchAutoSuggestion.getTerms();
            return actualSearchTerm;
        }
        return null;
    }
    
    public static String assignWildCardsToSearchTerm(String wildCard, String searchTerm) {
        String newSeachTerm = new String();
        if (wildCard.equalsIgnoreCase(R)) {
            newSeachTerm = searchTerm + "*";
        } else if (wildCard.equalsIgnoreCase(L)) {
            newSeachTerm = "*" + searchTerm;
        } else if (wildCard.equalsIgnoreCase(B)) {
            newSeachTerm = "*" + searchTerm + "*";
        } else {
            newSeachTerm = searchTerm;
        }
        return newSeachTerm;
    }

    
    public static List generalCorrectionSetup(Navigation nav){
        List eSearchAutoSuggestionsList = new ArrayList();
        Map<String, ESearchReport> eSearchReports = nav.getESearchReports();
        if (eSearchReports != null) {
            for (Map.Entry<String, ESearchReport> entry : eSearchReports.entrySet()) {
                ESearchReport eSearchReport = entry.getValue();

                if (eSearchReport != null) {
                    eSearchAutoSuggestionsList = eSearchReport.getAutoSuggestions();
                    return eSearchAutoSuggestionsList;
                }
                  
            }
        }
        return null;
    }
    
    public static void printListedResults(List<EndecaItem> resultsList){
    	if (resultsList.size() > 0){
    		System.out.println("--------------------Started Printing Results------------------------");
        	for (EndecaItem item : resultsList){
        		item.prettyPrint();
        	}
        	System.out.println("--------------------Finished Printing Results------------------------");
    	}else{
    		System.out.println("Results list does not contain any Endeca items");
    	}
    	
    }

        

}
