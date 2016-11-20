/**
 * 
 */
package com.ec.containers.pojo;


/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 8 Apr 2016 at 12:42:39
 *
 * ************************************************************************************************
 * </pre>
 */
public abstract class GenericItem{

    public GenericItem(){
        
    }
    
    public Integer addOne(){
        return 1;
    }
    
    public abstract String getFamilyGroup();
    
    public abstract void prettyPrint();
}
