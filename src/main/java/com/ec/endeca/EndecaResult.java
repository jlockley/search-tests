/**
 * 
 */
package com.ec.endeca;

import java.util.ArrayList;
import java.util.List;

import com.ec.containers.pojo.EndecaItem;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 8 Apr 2016 at 10:25:48
 *
 * ************************************************************************************************
 * </pre>
 */
public class EndecaResult {
   
    private ArrayList<EndecaItem> endecaItems;
    private boolean spellingCorrection;
    private boolean autmaticPhrasing;
    private String actualSearchTerm;
    private String originalSearchTerm;

    public EndecaResult(ArrayList<EndecaItem> endecaItems, boolean spellingCorrection, boolean autmaticPhrasing,
        String actualSearchTerm, String originalSearchTerm) {
        this.endecaItems = endecaItems;
        this.spellingCorrection = spellingCorrection;
        this.autmaticPhrasing = autmaticPhrasing;
        this.actualSearchTerm = actualSearchTerm;
        this.originalSearchTerm = originalSearchTerm;
    }
    /**
     * @return the actualSearchTerm
     */
    public String getActualSearchTerm() {
        return actualSearchTerm;
    }
    /**
     * @return the originalSearchTerm
     */
    public String getOriginalSearchTerm() {
        return originalSearchTerm;
    }
    /**
     * @param actualSearchTerm the actualSearchTerm to set
     */
    public void setActualSearchTerm(String actualSearchTerm) {
        this.actualSearchTerm = actualSearchTerm;
    }
    /**
     * @param originalSearchTerm the originalSearchTerm to set
     */
    public void setOriginalSearchTerm(String originalSearchTerm) {
        this.originalSearchTerm = originalSearchTerm;
    }
    /**
     * @return the endecaItems
     */
    public List<EndecaItem> getEndecaItems() {
        return endecaItems;
    }

    /**
     * @return the spellingCorrection
     */
    public boolean getIsSpellingCorrection() {
        return spellingCorrection;
    }

    /**
     * @return the autmaticPhrasing
     */
    public boolean getIsisAutmaticPhrasing() {
        return autmaticPhrasing;
    }

    /**
     * @param endecaItems the endecaItems to set
     */
    public void setEndecaItems(ArrayList<EndecaItem> endecaItems) {
        this.endecaItems = endecaItems;
    }

    /**
     * @param spellingCorrection the spellingCorrection to set
     */
    public void setSpellingCorrection(boolean spellingCorrection) {
        this.spellingCorrection = spellingCorrection;
    }

    /**
     * @param autmaticPhrasing the autmaticPhrasing to set
     */
    public void setAutmaticPhrasing(boolean autmaticPhrasing) {
        this.autmaticPhrasing = autmaticPhrasing;
    }
    
    public void printResults(){
        System.out.println("\n----------------------General Results----------------------------");
        System.out.println("number of items : " + endecaItems.size());
        System.out.println("Original Query : " + getOriginalSearchTerm());
        System.out.println("Actual Query : " + getOriginalSearchTerm());
        System.out.println("autoPhrasing on : " + getIsisAutmaticPhrasing());
        System.out.println("autoSpelling on : " + getIsSpellingCorrection());
        System.out.println("------------------------------------------------------------------\n");
        
    }
    
    public void printResultsWithItems(){
        System.out.println("\n----------------------General Results With Items----------------------------");
        System.out.println("number of items : " + endecaItems.size());
        System.out.println("Original Query : " + getOriginalSearchTerm());
        System.out.println("Actual Query : " + getOriginalSearchTerm());
        System.out.println("autoPhrasing on : " + getIsisAutmaticPhrasing());
        System.out.println("autoSpelling on : " + getIsSpellingCorrection());
        for (Object item : endecaItems){
            ((EndecaItem) item).prettyPrint();
        }
        System.out.println("------------------------------------------------------------------\n");
        
        
    }



}
