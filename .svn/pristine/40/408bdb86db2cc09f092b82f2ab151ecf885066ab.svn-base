/**
 * 
 */
package com.ec.containers.test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 23 Mar 2016 at 15:20:53
 *
 * ************************************************************************************************
 * </pre>
 */

@Configuration
public class TestCase {
    
    private static final AtomicInteger idCount = new AtomicInteger(0);
    private int id;
    private String name;
    private List<String> type;
    private List<Query> query;
    private String env;
    private String locale;
    private String cascade = "1";
    private String logic = "0"; 
    
    public TestCase(String name, List<String> type, List<Query> query, String env, String locale, String cascade, String logic) {
        this.id = idCount.getAndIncrement();
        this.name = name;
        this.type = type;
        this.query = query;
        this.env = env;
        this.locale = locale;
        this.cascade = cascade;
        this.logic = logic; 
    }
    
    public TestCase(String name, List<String> type, List<Query> query, String env, String locale) {
        this.id = idCount.getAndIncrement();
        this.name = name;
        this.type = type;
        this.query = query;
        this.env = env;
        this.locale = locale;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the type
     */
    public List<String> getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(List<String> type) {
        this.type = type;
    }
    /**
     * @return the query
     */
    public List<Query> getQuerys() {
        return query;
    }
    /**
     * @param query the query to set
     */
    public void setQuerys(List<Query> query) {
        this.query = query;
    }
    /**
     * @return the env
     */
    public String getEnv() {
        return env;
    }
    /**
     * @param env the env to set
     */
    public void setEnv(String env) {
        this.env = env;
    }
    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }
    
    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
    /**
     * @return 
     * @return the search parameters
     */

	public String getCascade() {
		return cascade;
	}

	public void setCascade(String cascade) {
		this.cascade = cascade;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}
}
