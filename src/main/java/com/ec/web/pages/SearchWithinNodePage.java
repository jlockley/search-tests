package com.ec.web.pages;

import com.ec.web.drivers.WebDriverPageOps;

public class SearchWithinNodePage extends BasePage {

	public SearchWithinNodePage(WebDriverPageOps pageOps) {
		super(pageOps);
	}

	@Override
	public String getPageName() {
		return "SearchWithinNode";
	}

}
