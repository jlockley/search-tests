/**
 * 
 */
package com.ec.entry;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ec.analytics.AnalyticsChecker;
import com.ec.analytics.AnalyticsFactory;
import com.ec.analytics.AnalyticsResultsContainer;
import com.ec.analytics.AnalyticsWaitingRoomContainer;
import com.ec.configuration.AppConfig;
import com.ec.containers.test.TestCasesContainer;
import com.ec.quepid.bot.CupidHttpService;
import com.ec.quepid.bot.QupidBot;
import com.ec.quepid.bot.QupidBotFactory;
import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.scrappers.ItemSearcherScrapper;
import com.ec.web.scrappers.ScrapperFactory;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : Alex Young
 * Created : 18 Mar 2016 at 12:06:14
 *
 * ************************************************************************************************
 * </pre>
 */
public class Conductor {

    private ScrapperFactory scrapperFactory;   
    private TestCasesContainer startData;
    private ThreadPoolExecutor scrapperPool;
    private Integer maximumScrappers;
    private CupidHttpService cupidService;
    
    private AnnotationConfigApplicationContext ctx;
    private AnalyticsWaitingRoomContainer analyticsContainer;

    private ThreadPoolExecutor qupidPool;
    private QupidBotFactory qupidBotFactory;
    
    private ThreadPoolExecutor analyticsPool;
    private AnalyticsFactory analyticsFactory;
    private AnalyticsResultsContainer analyticsResultsContainer;
    
    private final CrawlerPropertiesReader crawlerProperties = new CrawlerPropertiesReader();
    private final EnvPropertiesReader envPropertiesReader = new EnvPropertiesReader();
    
    private static final Logger LOGGER = LogManager.getLogger(AnalyticsChecker.class);
    
    
    Conductor(){
        this.ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        this.scrapperFactory = new ScrapperFactory(crawlerProperties, envPropertiesReader);
        this.scrapperPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
        this.qupidPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.qupidBotFactory = new QupidBotFactory();
        this.cupidService = new CupidHttpService();
        
        this.maximumScrappers = crawlerProperties.getWebCrawlerNumber();
        
        this.analyticsContainer = new AnalyticsWaitingRoomContainer();
        this.analyticsPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.analyticsFactory = new AnalyticsFactory();
        this.analyticsResultsContainer = new AnalyticsResultsContainer();
    }
    
    //web
    public void beginScrapping(){
        for (int i = 0; i < maximumScrappers; i++) 
        {
            ItemSearcherScrapper itemScrapper = scrapperFactory.createItemSearcherScrapper(ctx.getBean(TestCasesContainer.class));
            LOGGER.info("A new itemScrapper has been added, crawler number:" + i);
            scrapperPool.execute(itemScrapper);
        }
    }
    
    public void stopScrapping(){
        scrapperPool.shutdown();
    }
    
    //Cupid
    public void beginCupidSearch(){
        TestCasesContainer testClasses = ctx.getBean(TestCasesContainer.class);
        for (int i = 0; i < 2; i++) {
            QupidBot quidBot = qupidBotFactory.createCupidBot(testClasses, analyticsContainer);
            LOGGER.info("A new qupidbot has been added, number:" + i);
            qupidPool.execute(quidBot);
        }
    }
    
    public void stopCupidSearch(){
        qupidPool.shutdown();
    }
    
    
    //analytics
    public void startAnalytics(){
        
        for (int i = 0; i < 1; i++) 
        {
            AnalyticsChecker analyticsBot = analyticsFactory.createAnalyticsChecker(analyticsContainer, analyticsResultsContainer);
            LOGGER.info("A new analytics bot has been added, bot number:" + i);
            analyticsPool.execute(analyticsBot);
        }
    }
    
    public void stopAnalytics(){
        analyticsPool.shutdown();
    }
    
    
}
