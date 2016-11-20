/**
 * 
 */
package com.ec.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.ec.endeca.EndecaConnection;


public class TestConfigReader {
    private Properties properties = new Properties();

    
    public TestConfigReader(){
        loadProperties();   
    }
    
    public String getTestConfigLocale(){
        return properties.getProperty("locale");
    }
    
    public String getTestConfigEnv(){
        return properties.getProperty("environment");
    }

    public EndecaConnection getEndecaConnGivenTestConfig() throws IndexOutOfBoundsException{
        String enumName = getTestConfigEnv() + getTestConfigLocale();
        for(EndecaConnection connName : EndecaConnection.values()){
            if(connName.name().equals(enumName.toUpperCase())){
                return connName;
            }
        };
        throw new IndexOutOfBoundsException(String.format("Please check config setting for endeca connection : {%s} enum connection does not exist", enumName));
    }
    
    public void loadProperties(){
    
        FileInputStream input = null;
        
        try {
        	input = new FileInputStream("D:\\Java\\workspace\\rs-components-search-relevancy-automation\\src\\main\\resources\\test_config.properties");
//            input = new FileInputStream("D:\\workspace\\rs-components-search-relevency-framework\\src\\main\\resources\\test_config.properties");     
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
