/**
 * 
 */
package com.ec.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 6 Apr 2016 at 17:18:53
 *
 * ************************************************************************************************
 * </pre>
 */
public class EnvPropertiesReader {
    private Properties properties = new Properties();

    
    public EnvPropertiesReader(){
        loadProperties();   
    }
    
    public String getStatic2EnvForLocale(String locale){
        String searchString = locale + "st2env";
        return properties.getProperty(searchString);
    }
    
    public String getPrePropEnvForLocale(String locale){
        String searchString = locale + "preprodenv";
        return properties.getProperty(searchString);
    }
    
    public String getProdEnvForLocale(String locale){
    	String searchString = locale + "prodenv"; 
    	return properties.getProperty(searchString);
    }
    
    public void loadProperties(){
    
        FileInputStream input = null;
        
        try {
        	input = new FileInputStream("D:\\Java\\workspace\\rs-components-search-relevancy-automation\\src\\main\\resources\\environment.properties");
//            input = new FileInputStream("D:\\workspace\\rs-components-search-relevency-framework\\src\\main\\resources\\environment.properties");     
            properties.load(input);            
    
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
