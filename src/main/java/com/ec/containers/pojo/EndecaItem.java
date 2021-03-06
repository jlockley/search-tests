/**
 * 
 */
package com.ec.containers.pojo;

import java.util.Comparator;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 8 Apr 2016 at 09:14:54
 *
 * ************************************************************************************************
 * </pre>
 */
public class EndecaItem extends GenericItem implements Comparator<EndecaItem>, Comparable<EndecaItem>{
    
    String id;
    String famName;
    String brand;
    String longDesc;
    String manPartNum;
    String primaryKeywrd;
    String additionalSearchTerms;
    String familyID;
    String attributes;
    String groupNbr;
    String searchDiscon;
    String locale;
    String imageURL;
    String relevancyOrder;
    
    public EndecaItem(String id, String famName, String brand, String longDesc, String manPartNum, String primaryKeywrd,
            String additionalSearchTerms, String familyID, String attributes, String groupNbr, String searchDiscon, String locale, String imageURL, String relevancyOrder) {
        
        this.id = id;
        this.famName = famName;
        this.brand = brand;
        this.longDesc = longDesc;
        this.manPartNum = manPartNum;
        this.primaryKeywrd = primaryKeywrd;
        this.additionalSearchTerms = additionalSearchTerms;
        this.familyID = familyID;
        this.attributes = attributes;
        this.groupNbr = groupNbr; 
        this.searchDiscon = searchDiscon;
        this.imageURL = imageURL;
        this.locale = locale;
        this.relevancyOrder = relevancyOrder;
    }
    
    public EndecaItem(String id, String famName, String brand, String longDesc, String manPartNum, String primaryKeywrd,
            String additionalSearchTerms, String familyID) {
        
        this.id = id;
        this.famName = famName;
        this.brand = brand;
        this.longDesc = longDesc;
        this.manPartNum = manPartNum;
        this.primaryKeywrd = primaryKeywrd;
        this.additionalSearchTerms = additionalSearchTerms;
        this.familyID = familyID;
        
    }
    


	/**
     * @return the getId
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return the famName
     */
    public String getFamName() {
        return famName;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the longDesc
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * @return the manPartNum
     */
    public String getManPartNum() {
        return manPartNum;
    }

    /**
     * @return the primaryKeywrd
     */
    public String getFamilyGroup() {
        return familyID;
    }

    /**
     * @return the additionalSearchTerms
     */
    public String getAdditionalSearchTerms() {
        return additionalSearchTerms;
    }
    
    public String getFamilyID(){
        return familyID;
    }
    
    public String getRelevancyOrder() {
		return relevancyOrder;
	}
    

	public void setrelevancyOrder(String relevancyOrder) {
		this.relevancyOrder = relevancyOrder;
	}
	
	public String getAttributes(){
	    return this.attributes;
	}
	
	public void setAttributes(String attributes){
	    this.attributes = attributes;
    }
	
	public String getGroupNbr(){
		return this.groupNbr;
	}
	
	public void setGroupNbr(String groupNbr){
		this.groupNbr = groupNbr;
	}
	
	public String getSearchDiscon() {
		return this.searchDiscon;
	}

	public void setSearchDiscon(String searchDiscon) {
		this.searchDiscon = searchDiscon;
	}

    public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public void prettyPrint(){

        System.out.println("-------------------ENDECA ITEM--------------------------");
        System.out.println("id :" + id);
        System.out.println("famName :" + famName);
        System.out.println("brand :" + brand);
        System.out.println("longDesc :" + longDesc);
        System.out.println("manPartNum :" + manPartNum);
        System.out.println("primaryKeywrd :" + primaryKeywrd);
        System.out.println("additionalSearchTerms :" + additionalSearchTerms);
        System.out.println("familyId :" + familyID);
        System.out.println("attributes :" + attributes);
        System.out.println("groupNbr :" + groupNbr);
        System.out.println("Discontinued: " + searchDiscon);
        System.out.println("--------------------------------------------------------");
    }

    @Override
    public int compareTo(EndecaItem o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public int compare(EndecaItem o1, EndecaItem o2) {
        return o1.longDesc.compareTo(o2.longDesc);
    }


}
