package com.ec.configuration;
import java.util.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ec.containers.test.TestCasesContainer;
import com.ec.datastubs.TestDataStub;

/**
 * 
 */

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 24 Mar 2016 at 09:13:03
 *
 * ************************************************************************************************
 * </pre>
 */
@Configuration
public class AppConfig {
    
    @Bean
    @Scope("prototype")
    public TestCasesContainer testcases(){
        Queue testData = new TestDataStub().getTestCases();
        return new TestCasesContainer(testData);
    }

}
