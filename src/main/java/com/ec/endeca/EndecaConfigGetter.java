/**
 * 
 */
package com.ec.endeca;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.pojo.EndecaItem;
import com.endeca.navigation.DimValIdList;
import com.endeca.navigation.ENEQuery;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.ENEQueryResults;
import com.endeca.navigation.ERecSearch;
import com.endeca.navigation.ERecSearchList;
import com.endeca.navigation.HttpENEConnection;
import com.endeca.navigation.Navigation;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 11 Apr 2016 at 08:29:53
 *
 * ************************************************************************************************
 * </pre>
 */
public class EndecaConfigGetter {
    

    private static final Logger LOGGER = LogManager.getLogger(EndecaConfigGetter.class);

    private static final String L = "LEFT";
    private static final String R = "RIGHT";
    private static final String B = "BOTH";
    private static final String N = "NONE";
    private static final DimValIdList DEFAULT_VALUE_ID = new DimValIdList("0");
    
    private ArrayList<EndecaItem> blankEndecaItemList;
    private boolean spellingCorrection = false;
    private boolean autoPhrasing = false;
    private ArrayList<EndecaItem> searchResultsList;
    private String actualQuery;
    private String searchTerm;
    private EndecaResult endecaResult;


    
    public boolean getSpellingCorrectionStatus(String searchInterface, HttpENEConnection endecaConn, String searchTerm, String opts, String wildCard) throws ENEQueryException{
        ENEQueryResults result = getEndecaResults(searchInterface, endecaConn, searchTerm, opts,  wildCard);
        if (result.containsNavigation()) {
            Navigation nav = result.getNavigation();
            autoPhrasing = EndecaHelpers.checkForAutomaticPhrasing(nav);
            actualQuery = EndecaHelpers.getActualSearchTerm(nav);
            LOGGER.warn(actualQuery);
            return EndecaHelpers.checkFromSpellingCorrection(nav);
        }else{
            return false;
        }
    }
    
    public boolean getAutoCorrectionStatus(String searchInterface, HttpENEConnection endecaConn, String searchTerm, String opts, String wildCard) throws ENEQueryException{
        ENEQueryResults result = getEndecaResults(searchInterface, endecaConn, searchTerm, opts,  wildCard);
        if (result.containsNavigation()) {
            Navigation nav = result.getNavigation();
            //actualQuery = getActualSearchTerm(nav);
            return EndecaHelpers.checkForAutomaticPhrasing(nav);
        }else{
            return false;
        }
    }

    private ENEQueryResults getEndecaResults(String searchInterface, HttpENEConnection endecaConn, String searchTerm, String opts, String wildCard)
            throws ENEQueryException {
        
        searchTerm = EndecaHelpers.assignWildCardsToSearchTerm(wildCard, searchTerm);
        ENEQuery endecaQuery = makeEndecaQuery(searchInterface, searchTerm, opts);
        return executeEndecaQuery(endecaConn, endecaQuery);
    }

  

    private ENEQuery makeEndecaQuery(String searchInterface, String searchTerm, String opts)
            throws ENEQueryException {

        ENEQuery eneQuery = new ENEQuery();
        ERecSearch eRecSearch = new ERecSearch(searchInterface, searchTerm, opts);
        ERecSearchList eRecSearchList = new ERecSearchList();
        eRecSearchList.add(eRecSearch);
        eneQuery.setNavERecSearches(eRecSearchList);
        eneQuery.setNavDescriptors(DEFAULT_VALUE_ID);
        eneQuery.setNavERecsOffset(0);
        eneQuery.setNavNumERecs(1000);
        eneQuery.setNavAllRefinements(true);
        eneQuery.setNavERecSearchComputeAlternativePhrasings(true);
        eneQuery.setNavERecSearchRewriteQueryWithAnAlternativePhrasing(true);
        eneQuery.setLanguageId("en");
        eneQuery.setNavRecordFilter("&Nr=P_localeLifecycleStatus:90");
//        NOT(P_packType:PRODUCTION_PACKED))
        return eneQuery;

    }
    
    private ENEQueryResults executeEndecaQuery(HttpENEConnection endecaConn ,ENEQuery eneQuery) throws ENEQueryException{
        ENEQueryResults result = endecaConn.query(eneQuery);
        return result;
    }


}
    
    
