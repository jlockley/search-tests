package com.ec.web.pages;

import org.openqa.selenium.By;

import com.ec.web.drivers.WebDriverPageOps;

public class BrandPage extends BasePage {
	
	private final By BRAND_NAME_IDENTIFIER = By.cssSelector("meta[itemprop='name']");

	public BrandPage(WebDriverPageOps pageOps) {
		super(pageOps);
	}
	
	public String getBrandName(){
		return getPageOps().getAttributeFromElem(BRAND_NAME_IDENTIFIER, "content");
	}
	
	@Override
	public String getPageName() {
		return ("Brand Page");
	}

}
