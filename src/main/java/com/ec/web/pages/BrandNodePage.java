package com.ec.web.pages;

import org.openqa.selenium.By;

import com.ec.web.drivers.WebDriverPageOps;

public class BrandNodePage extends BasePage {
	
	private final By BRAND_NAME_IDENTIFIER = By.cssSelector("meta[itemprop='name']");
	private final By SPECIFIC_NODE_IDENTIFIER = By.cssSelector(".subheader");

	public BrandNodePage(WebDriverPageOps pageOps) {
		super(pageOps);
	}
	
	public String getBrandName(){
		return getPageOps().getAttributeFromElem(BRAND_NAME_IDENTIFIER, "content");
	}
	
	public String getNodeName(){ 
		return getPageOps().getElementText(SPECIFIC_NODE_IDENTIFIER);
	}
	
	@Override
	public String getPageName() {
		return null;
	}

}
