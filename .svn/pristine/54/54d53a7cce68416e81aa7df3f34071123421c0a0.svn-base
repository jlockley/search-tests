/**
 * 
 */
package com.ec.quepid.bot;

import com.ec.analytics.AnalyticsWaitingRoomContainer;
import com.ec.containers.test.TestCase;
import com.ec.containers.test.TestCasesContainer;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 23 Mar 2016 at 16:10:26
 *
 * ************************************************************************************************
 * </pre>
 */
public class QupidBotFactory {
    
    public QupidBot createCupidBot(TestCasesContainer testCases, AnalyticsWaitingRoomContainer analyticsContainer){
        return new QupidBot(testCases, analyticsContainer);
    }
    
    public NoThreadCupidBot createNonThreadCupidBot(){
        return new NoThreadCupidBot();
    }

}
