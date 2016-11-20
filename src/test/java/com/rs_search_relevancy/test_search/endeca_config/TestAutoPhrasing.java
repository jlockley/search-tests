/**
 * 
 */
package com.rs_search_relevancy.test_search.endeca_config;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ec.endeca.EndecaConfigGetter;
import com.ec.endeca.EndecaConnection;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.HttpENEConnection;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 11 Apr 2016 at 08:55:36
 *
 * ************************************************************************************************
 * </pre>
 */

@RunWith(Parameterized.class)
public class TestAutoPhrasing {

    private String searchInterface;
    private String searchTerm;
    private String wildCard;
    private boolean expectedResult;
    
    @Before
    public void initialize() {
    }
    

    public TestAutoPhrasing(String searchInterface, String searchTerm, String wildCard, boolean expectedResult) {
        this.searchInterface = searchInterface;
        this.searchTerm = searchTerm;
        this.wildCard = wildCard;
        this.expectedResult = expectedResult;
    }
    
    @Parameterized.Parameters(name = "{1} : triggers auto phrasing : {3}")
    public static Collection<Object[]> createTestData() {
        return Arrays.asList(new Object[][] { 
            {"I18NLDescTaxonomyBrandSearchTerm2", "24 V Power", "N", true }, 
            {"I18NLDescTaxonomyBrandSearchTerm2", "10 uf", "N", true }, 
            {"I18NLDescTaxonomyBrandSearchTerm2", "20 nf", "N", true }, 
            {"I18NLDescTaxonomyBrandSearchTerm2", "15 pf", "N", true }, 
            {"I18NLDescTaxonomyBrandSearchTerm2", "30 F", "N", true }, 
            
        
        });
    }

    @Test
    public void autoPhrasingTest() {

        EndecaConfigGetter endecaConfigTester = new EndecaConfigGetter();
        HttpENEConnection endecaConn = EndecaConnection.PREPUK.getConnection();
        boolean result = false;
        String opts = "mode matchall";

        try {
            result = endecaConfigTester.getAutoCorrectionStatus(searchInterface, endecaConn, searchTerm, opts, wildCard);
        } catch (ENEQueryException e) {
            e.printStackTrace();
        }
        
        if(expectedResult){
            Assert.assertTrue(String.format("asserting {%s} does trigger auto phrasing",searchTerm),result);
        }else{
            Assert.assertFalse(String.format("asserting {%s} does not trigger auto phrasing", searchTerm),result);
        }

    }   

}
