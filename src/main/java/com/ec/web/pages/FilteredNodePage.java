package com.ec.web.pages;

import com.ec.web.drivers.WebDriverPageOps;

public class FilteredNodePage extends BasePage {

	public FilteredNodePage(WebDriverPageOps pageOps) {
		super(pageOps);
	}

	@Override
	public String getPageName() {
		return "FilteredNodePage";
	}

}
