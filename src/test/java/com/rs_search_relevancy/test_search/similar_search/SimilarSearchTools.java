/**
 * 
 */
package com.rs_search_relevancy.test_search.similar_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.annotation.NotThreadSafe;

import com.ec.analytics.checks.generics.groupItemGenerics;
import com.ec.containers.pojo.EndecaItem;
import com.ec.endeca.EndecaResult;

import org.junit.Assert;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 12 Apr 2016 at 09:40:03
 *
 * ************************************************************************************************
 * </pre>
 */

@NotThreadSafe
public class SimilarSearchTools {
       
    @SuppressWarnings("unchecked")
	static List<TreeSet<EndecaItem>> compareResults(String family, EndecaResult result1, EndecaResult result2) {
    	
        TreeSet<EndecaItem> itemsInChosenFamilyOne; 
        TreeSet<EndecaItem> itemsInChosenFamilyTwo; 
        List<TreeSet<EndecaItem>> uniqueItems = new ArrayList<TreeSet<EndecaItem>>();
        
        Map<String, List<EndecaItem>> familyList1 = groupItemGenerics.groupByFamilyEndecaList(result1.getEndecaItems());
        Map<String, List<EndecaItem>> familyList2 = groupItemGenerics.groupByFamilyEndecaList(result2.getEndecaItems());
        
        try{
            itemsInChosenFamilyOne = new TreeSet<EndecaItem>(familyList1.get(family));
            itemsInChosenFamilyTwo = new TreeSet<EndecaItem>(familyList2.get(family));
        }catch(NullPointerException e){
            throw new NullPointerException(String.format("{%s} is not present in both products", family));
        }

        TreeSet<EndecaItem> itemsInChosenFamilyOneClone = (TreeSet<EndecaItem>) itemsInChosenFamilyOne.clone();

        itemsInChosenFamilyOne.removeAll(itemsInChosenFamilyTwo);
        itemsInChosenFamilyTwo.removeAll(itemsInChosenFamilyOneClone);

        uniqueItems.add(0, itemsInChosenFamilyOne);
        uniqueItems.add(1, itemsInChosenFamilyTwo);
      
        return uniqueItems;

    }
    
    @SuppressWarnings("unchecked")
	public static List<TreeSet<EndecaItem>> removeProductsWithoutAttribute(List<TreeSet<EndecaItem>> uniqueItemList, String searchTerm){
    	TreeSet<EndecaItem> resultSetOne = uniqueItemList.get(0);
    	TreeSet<EndecaItem> resultSetTwo = uniqueItemList.get(1);
    	TreeSet<EndecaItem> resultSetsCombined = resultSetOne;
    	resultSetsCombined.addAll(resultSetTwo);
    	TreeSet<EndecaItem> productsWithoutAttribute = new TreeSet<EndecaItem>();
    	
		String stringPattern = "(.*\\d+[Aa-zZ])|(.*\\d+ [Aa-zZ])";
		Pattern pattern = Pattern.compile(stringPattern);
		Matcher matcher = pattern.matcher(searchTerm);
		Boolean b = matcher.find();
		String uom = matcher.group(0);
    	for (EndecaItem product : resultSetsCombined){
    		String attr = product.getAttributes();
    		if(attr != null){
        		if(!StringUtils.containsIgnoreCase(attr, uom)){
        			productsWithoutAttribute.add(product);
        		}
    		}else{
    			System.out.println("Unique product: " + product.getGroupNbr() + " in family: " + product.getFamilyGroup() + " does not have any attributes");
    			productsWithoutAttribute.add(product);
    		}
    	}
    	resultSetOne.removeAll(productsWithoutAttribute);
    	resultSetTwo.removeAll(productsWithoutAttribute);
    	
    	uniqueItemList.add(0, resultSetOne);
    	uniqueItemList.add(1, resultSetTwo);
        
    	return uniqueItemList;
    }
    
    @SuppressWarnings("unchecked")
	private TreeSet<EndecaItem> removeRelevantUniqueItems(TreeSet<EndecaItem> itemList, String term1, String term2){
    	TreeSet<EndecaItem> itemListClone = (TreeSet<EndecaItem>) itemList.clone();
    	List<String[]> splitTerms = new ArrayList<String[]>();
    	splitTerms.add(term1.split(" "));
    	splitTerms.add(term2.split(" "));
        for (EndecaItem item : itemList){
        	for(String[] section : splitTerms){
        		for(String term : section){ 
        			String additionalTerms = item.getAdditionalSearchTerms();
        			String desc = item.getLongDesc();
                	if(desc.contains(term)){
                		itemListClone.remove(item);
                	}else if(additionalTerms.contains(term)){
                		itemListClone.remove(item);
                	}
        		}
        	}

        }
        return itemListClone;
    }

    
    @SuppressWarnings("unused")
	private void printUniqueItems(TreeSet<EndecaItem> uniqueProductsOne, TreeSet<EndecaItem> uniqueProductsTwo,
    		String term1, String term2){
        if(!uniqueProductsOne.isEmpty() || !uniqueProductsTwo.isEmpty()){
        	uniqueProductsOne = removeRelevantUniqueItems(uniqueProductsOne, term1, term2);
            System.out.println("\n---------------------------unique items in " + term1
            	       + " ----------------------\n");
     		for (EndecaItem item : uniqueProductsOne) {
     		   item.prettyPrint();
     		}
     		uniqueProductsTwo = removeRelevantUniqueItems(uniqueProductsTwo, term1, term2);
    		System.out.print("\n---------------------------unique items in " + term2
    			       + "  ----------------------\n");
			for (EndecaItem item : uniqueProductsTwo) {
			   item.prettyPrint();
			}
         }
    }
    
    public static List<EndecaItem> checkItemsInList(List<EndecaItem> listToCheckAgainst, List<EndecaItem> items){
        TreeSet<EndecaItem> itemsInMainSet;
        TreeSet<EndecaItem> itemsInCheckSet;
        itemsInMainSet = new TreeSet<EndecaItem>(listToCheckAgainst);
        itemsInCheckSet = new TreeSet<EndecaItem>(items);
        itemsInCheckSet.removeAll(itemsInMainSet);
        List returnItems = new ArrayList<EndecaItem>();
        returnItems.addAll(itemsInCheckSet);
        return returnItems;
      
    }
    
     
}
