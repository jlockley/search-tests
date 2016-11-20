/**
 * 
 */
package com.ec.quepid.bot;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;
import com.ec.containers.test.TestCasesContainer;

public class NoThreadCupidBot {

    private static final Logger LOGGER = LogManager.getLogger(QupidBot.class);
    private TestCasesContainer testCases;
    private CupidHttpService cupidHttpService;


    public NoThreadCupidBot() {
        this.cupidHttpService = new CupidHttpService();
    }

    public void getResults(TestCase testCase) {
        for (Query query : testCase.getQuerys()) {
            String term = query.getQuery();
            
            try {
                CupidHttpResult results = cupidHttpService.get(term, testCase.getLocale(), testCase.getEnv());
                query.setCupidHttpResult(results);
            } catch (ClientProtocolException e) {
                LOGGER.error("Error in getting results from cupid for test :{" + testCase.getName() + "} for query :" + query.getQuery());
                query.setCupidHttpResult(null);
            } catch (IOException e) {
                LOGGER.error("Error in getting results from cupid for test :{" + testCase.getName() + "} for query :" + query.getQuery());
                query.setCupidHttpResult(null);
            }
        }
    }
    
    public CupidHttpResult getResults(String term, String locale, String env){
    	return getResults(term, locale, env, "0", "0");
    }
    
    public CupidHttpResult getResults(String term, String locale, String env, String cascade, String logic) {     
            try {
                CupidHttpResult results = cupidHttpService.get(term, locale, env, cascade, logic);
                return results;
            } catch (ClientProtocolException e) {
                LOGGER.error("Error in getting results from cupid search " + term + " for casade " + cascade + " and logic " + logic);
                return null;
            } catch (IOException e) {
                LOGGER.error("Error in getting results from cupid search " + term + " for casade " + cascade + " and logic " + logic);
                return null;
            }
        }
    }
