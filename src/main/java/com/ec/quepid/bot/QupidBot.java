/**
 * 
 */
package com.ec.quepid.bot;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.analytics.AnalyticsWaitingRoomContainer;
import com.ec.containers.test.Query;
import com.ec.containers.test.TestCase;
import com.ec.containers.test.TestCasesContainer;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : Alex Young
 * Created : 23 Mar 2016 at 14:43:33
 *
 * ************************************************************************************************
 * </pre>
 */
public class QupidBot implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(QupidBot.class);
    private TestCasesContainer testCases;
    private CupidHttpService cupidHttpService;
    private AnalyticsWaitingRoomContainer analyticsContainer;
    private volatile boolean running;

    public QupidBot(TestCasesContainer testCases, AnalyticsWaitingRoomContainer analyticsContainer) {
        this.testCases = testCases;
        this.analyticsContainer = analyticsContainer;
        this.cupidHttpService = new CupidHttpService();
        this.running = true;
    }

    @Override
    public void run() {
        Integer blankCount = 0;
        while (running) {
            TestCase testCase = testCases.getTestFromQueue();
            if (testCase != null) {
                System.out.println("get new test, Thread name :" + Thread.currentThread().getName());
                getResults(testCase);
                analyticsContainer.putTestInQueue(testCase);
            } else {
                if (blankCount > 20) {
                    running = false;
                }
                blankCount++;
            }
       }

    }

    public void getResults(TestCase testCase) {
        for (Query query : testCase.getQuerys()) {
            String term = query.getQuery();
            try {
                CupidHttpResult results = cupidHttpService.get(term, "uk", "st2");
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
}
