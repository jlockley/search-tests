/**
 * 
 */
package com.ec.web.containers;


/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 22 Mar 2016 at 09:11:30
 *
 * ************************************************************************************************
 * </pre>
 */
public class Category {
    private String name;
    private String href;
    private String imageHref;
    
    public Category(String name, String href, String imageHref){
        this.name = name;
        this.href = href;
        this.imageHref = imageHref;
    }
    
    public String getName(){
        return name;
    }
    
    public String getHref(){
        return href;
    }
    
    public String getImageHref(){
        return imageHref;
    }
    
    public void printCategory(){
        System.out.println(name);
        System.out.println(href);
        System.out.println(imageHref);
        System.out.println(" ------------------------------------------ ");
    }

}
