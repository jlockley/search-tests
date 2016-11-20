/**
 * 
 */
package com.ec.web.scrappers;

import com.ec.containers.test.TestCasesContainer;
import com.ec.utils.CrawlerPropertiesReader;
import com.ec.utils.EnvPropertiesReader;
import com.ec.web.drivers.WebDriverPageOps;
import com.ec.web.drivers.WebDriverPageOpsFactory;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 18 Mar 2016 at 11:22:06
 *
 * ************************************************************************************************
 * </pre>
 */
public class ScrapperFactory {
    
    private final WebDriverPageOpsFactory webdriverfactory;
    private final CrawlerPropertiesReader crawlerProperties;
    private final EnvPropertiesReader envProperties;

    public ScrapperFactory(CrawlerPropertiesReader crawlerProperties, EnvPropertiesReader envProperties){
        this.envProperties = envProperties;
        this.crawlerProperties = crawlerProperties;
        this.webdriverfactory = new WebDriverPageOpsFactory(crawlerProperties.getIEPath(),crawlerProperties.getChromePath());
    }
    
    public ItemSearcherScrapper createItemSearcherScrapper(TestCasesContainer startData){
        WebDriverPageOps pageOps = webdriverfactory.create(crawlerProperties.getDriverName(), crawlerProperties.getImplicitTimeout(),"");
        return new ItemSearcherScrapper(pageOps, startData);
    }
    
    public NoThreadGeneralWebBot getNonThreadGeneralBot(String locale, String env){
        String baseUrl = null;
        if(env.equals("st2")){
            baseUrl = envProperties.getStatic2EnvForLocale(locale);
        }else if(env.equals("prep")){
            baseUrl = envProperties.getPrePropEnvForLocale(locale);
        }else{
        	baseUrl = envProperties.getProdEnvForLocale(locale);
        }
        WebDriverPageOps pageOps = webdriverfactory.create(crawlerProperties.getDriverName(), crawlerProperties.getImplicitTimeout(),baseUrl);
        return new NoThreadGeneralWebBot(pageOps);
    }
}
