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

@RunWith(Parameterized.class)
public class TestSpellingCorrection {

    private String searchInterface;
    private String searchTerm;
    private String wildCard;
    private boolean expectedResult;
    
    @Before
    public void initialize() {
    }
    

    public TestSpellingCorrection(String searchInterface, String searchTerm, String wildCard, boolean expectedResult) {
        this.searchInterface = searchInterface;
        this.searchTerm = searchTerm;
        this.wildCard = wildCard;
        this.expectedResult = expectedResult;
    }
    
    @Parameterized.Parameters(name = "{1} : triggers spelling correction : {3}")
    public static Collection<Object[]> createTestData() {
        return Arrays.asList(new Object[][] { 
            {"I18NLDescTaxonomyBrandSearchTerm2", "10 V capacitr", "N", true }, 
        });
    }

    @Test
    public void spellingCheckTest() {

        EndecaConfigGetter endecaConfigTester = new EndecaConfigGetter();
        HttpENEConnection endecaConn = EndecaConnection.PREPUK.getConnection();
        boolean result = false;
        String opts = "mode matchall";

        try {
            result = endecaConfigTester.getSpellingCorrectionStatus(searchInterface, endecaConn, searchTerm, opts, wildCard);
        } catch (ENEQueryException e) {
            e.printStackTrace();
        }
        
        if(expectedResult){
            Assert.assertTrue(String.format("asserting {%s} does trigger spelling correction",searchTerm),result);
        }else{
            Assert.assertFalse(String.format("asserting {%s} does not trigger spelling correction", searchTerm),result);
        }

    }   

}