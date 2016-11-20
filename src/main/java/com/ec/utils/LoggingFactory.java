/**
 * 
 */
package com.ec.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 18 Mar 2016 at 09:26:45
 *
 * ************************************************************************************************
 * </pre>
 */
public class LoggingFactory {
    
    private Integer loggerCount;
    
    public LoggingFactory(){
        this.loggerCount = 1;
    }
    
    
    public Logger createLogger(String name){
        Logger logger = LogManager.getFormatterLogger(name);
        return logger;
    }

}
