package com.rs_search_relevancy.test_search.quepid;

import java.util.ArrayList;

public class TestQuepidInvalidVariableData {
	
	public static ArrayList<String> getListOfValidEnvironments(){
		ArrayList<String> validEnvironments = new ArrayList<String>() {{
		    add("ssp");
		    add("st2");
		    add("st1");
		}};
		return validEnvironments;
		}
	
	public static ArrayList<String> getListOfValidLocales(){
		ArrayList<String> validLocales = new ArrayList<String>() {{
		    add("uk");
		    add("it");
		    add("de");
		}};
		return validLocales;
		}
	
	}

