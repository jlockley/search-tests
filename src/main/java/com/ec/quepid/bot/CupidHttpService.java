/**
 * 
 */
package com.ec.quepid.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ec.containers.pojo.CupidItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 22 Mar 2016 at 16:40:53
 *
 * ************************************************************************************************
 * </pre>
 */

//http://ecllapjitls901.ebs.ecomp.com:9999/quepidTranslator/QuepidTranslator?q=12+volt&locale=uk&=null&env=st2&fl=iduk%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text&wt=xml&debug=true&debug.explain.structured=true&hl=true&hl.fl=iduk%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text&hl.simple.pre=aouaoeuCRAZY_STRING!8_______&hl.simple.post=62362iueaiCRAZY_POST_STRING!_______&indent=true&echoParams=all

public class CupidHttpService {
	
    private static final Logger LOGGER = LogManager.getLogger(CupidHttpService.class);
      

    public CupidHttpService(){

    }
    
    /**
     * 
     * @param query : query string:
     * @param locale : country of origin:   United Kingdom:UK,  Italy:IT : France:FR etc
     * @param env : environment to target, st2:static2 ssb:sandbox etc
     * @throws ClientProtocolException : faulty url most likely cause
     * @throws IOException: GSON not mapped correctly to POJO most likely cause
     */

    
    public CupidHttpResult get(String query, String locale, String env, String cascade, String logic) throws ClientProtocolException, IOException{
    	
    	ArrayList<CupidItem> cupidItems;
 
    	HttpResponse result = makeRequest(query, locale, env, cascade, logic);
        StringBuffer jsonSB = new StringBuffer(EntityUtils.toString(result.getEntity(), "UTF-8"));
        boolean validSearchLogic = checkValidSearchLogic(jsonSB);
        boolean validSearchConfig = checkValidSearchConfig(jsonSB);
        boolean validEnvironmentVariable = checkInvalidEnvironment(jsonSB);
        boolean validLocaleVariable = checkInvalidLocale(jsonSB);
        Integer numberOfProducts = getNumberOfProductsFromResponse(jsonSB);
        Integer numberOfCategories = getNumberOfCategories(jsonSB);
        if(numberOfProducts > 0){
        	String json = manipulateResultToIncludeItemsOnly(jsonSB);
        	cupidItems = covertJsonToItemPOJOs(json);
        }else{
        	cupidItems = new ArrayList<CupidItem>();
        }
        
        CupidHttpResult httpResult = new CupidHttpResult(cupidItems, numberOfProducts, numberOfCategories, validSearchLogic, validSearchConfig, validEnvironmentVariable, validLocaleVariable);
        return httpResult;
    }
    
    public CupidHttpResult get(String query, String locale, String env) throws ClientProtocolException, IOException{
    	return get(query, locale, env, "0","0" );

    }
    
    private HttpResponse makeRequest(String query, String locale, String env, String cascade, String logic) throws ClientProtocolException, IOException{
        String logicQueryParam = logic=="0" ? "" : "&sl=" + logic;
        String cascadeQueryParam = cascade=="0" ? "" : "&sc=" + cascade;
    	String query_ws_rm = query.replace(" ", "%20");      
        // String url = "localhost:8080/quepidTranslator/QuepidTranslator?q=" + query_ws_rm  + "&locale=" + locale + "&env="  + env +  "&fl=id" + locale + "%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text&wt=xml&debug=true&debug.explain.structured=true&hl=true&hl.fl=iduk%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text";
        String url = "http://ecllapjitls901.ebs.ecomp.com:9999/quepidTranslator/QuepidTranslator?q=" + query_ws_rm  + "&locale=" + locale + "&env="  + env + cascadeQueryParam + logicQueryParam + "&fl=iduk" + locale + "%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text&wt=xml&debug=true&debug.explain.structured=true&hl=true&hl.fl=iduk%20P_longDescription%20P_imagePrimary%20P_imagePrimary%20familyName%20code_text%20P_manufacturerPartNumber%20P_brand%20P_primaryKeyword%20sectionName_text%20additionalSearchTerms_text%20AttributeNameValue_text";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "RS-AUTOMATION");
        HttpResponse result = client.execute(request);
        return result;
    }
    
    private Integer getNumberOfProductsFromResponse(StringBuffer jsonSB){
    	Integer numberOfProducts;
    	try{
	        String productsFoundString = jsonSB.substring(jsonSB.indexOf("{\"numFound\":") + 12, jsonSB.indexOf(",\"start\":"));
	        String productsFound = productsFoundString.substring(productsFoundString.indexOf(",") + 2, productsFoundString.length() - 1);
	        numberOfProducts = Integer.valueOf(productsFound); 
	   }catch(StringIndexOutOfBoundsException e){
		   //LOGGER.warn("No results were returned for getNumberOfProducts, due to StringIndexOutOfBoundsException returning 0!");
		   return 0;
	   }
        return numberOfProducts;
    }
    
    private boolean checkValidSearchConfig(StringBuffer jsonSB){
    	if(jsonSB.indexOf("Invalid Search Config") == -1){
    		return true;	
    	};
    	return false;
    }
    
    private boolean checkValidSearchLogic(StringBuffer jsonSB){
    	if(jsonSB.indexOf("Invalid Search Logic") == -1){
    		return true;
    	};
    	return false;
    }
    
    private boolean checkInvalidEnvironment(StringBuffer jsonSB){
    	if(jsonSB.indexOf("environment") == -1){
    		return true; 
    	}
    	return false;    	
    }
    
    private boolean checkInvalidLocale(StringBuffer jsonSB){
    	if(jsonSB.indexOf("locale parameter") == -1){
    		return true; 
    	}
    	return false;    	
    }
    
    
    
    private Integer getNumberOfCategories(StringBuffer jsonSB){
        String categoriesFoundString = new String();
        Integer numberOfCategories;
        try{
           categoriesFoundString = jsonSB.substring(jsonSB.indexOf("{\"numFound\":") + 12, jsonSB.indexOf(",\"start\":"));
           String categoriesFound = categoriesFoundString.substring(1, categoriesFoundString.indexOf("Categories,") - 1);
           numberOfCategories = Integer.valueOf(categoriesFound); 
        }catch(StringIndexOutOfBoundsException e){
 		   //LOGGER.warn("No results were returned for getNumberOfCategories, due to StringIndexOutOfBoundsException returning 0!");
            return 0;
        }
        return numberOfCategories;
    }
    
    private String manipulateResultToIncludeItemsOnly(StringBuffer jsonSB){
        Integer posOfCharToDeleteToFromBack = jsonSB.lastIndexOf("]");
        jsonSB.delete(posOfCharToDeleteToFromBack + 1, jsonSB.length()-1);
        jsonSB.delete(0,jsonSB.indexOf("docs\":[") + 6);
        String json = jsonSB.toString();
        return json;
    }
    
    private ArrayList<CupidItem> covertJsonToItemPOJOs(String json){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        CupidItem[] cupidItems = gson.fromJson(json, CupidItem[].class);
        ArrayList<CupidItem> items = new ArrayList<CupidItem>(Arrays.asList(cupidItems));   
        return items;
    }

        
}
