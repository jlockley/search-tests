package com.rs_search_relevancy.test_search.result_containers;

public class SimilarSearchResult {
	String term;
	String NumOfResults;
	String UniqueFamilies;
	String NumOfMatchedFamilies;
	String NumOfExpectedFamilies;
	
	public SimilarSearchResult(
			String term,
			String NumOfResults,
			String UniqueFamilies,
			String NumOfMatchedFamilies,
			String NumOfExpectedFamilies ){
		
		this.term = term;
		this.NumOfResults = NumOfResults;
		this.UniqueFamilies = UniqueFamilies;
		this.NumOfMatchedFamilies = NumOfMatchedFamilies;
		this.NumOfExpectedFamilies = NumOfExpectedFamilies;
	}
	
	public String getVariableFromString(String field){
		String returnVar = null;
		if (field.equalsIgnoreCase("term")){
			returnVar = getTerm();
		}else if(field.equalsIgnoreCase("NumOfResults")){
			returnVar = getNumOfResults();
		}else if(field.equalsIgnoreCase("UniqueFamilies")){
			returnVar = getUniqueFamilies();
		}else if(field.equalsIgnoreCase("NumOfMatchedFamilies")){
			returnVar = getNumOfMatchedFamilies();
		}else if(field.equalsIgnoreCase("NumOfExpectedFamilies")){
			returnVar = getNumOfExpectedFamilies();
		}else{
			System.out.println("String does not match any ResultObj fields" + field);
		}
		return returnVar;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getNumOfResults() {
		return NumOfResults;
	}

	public void setNumOfResults(String numOfResults) {
		NumOfResults = numOfResults;
	}

	public String getUniqueFamilies() {
		return UniqueFamilies;
	}

	public void setUniqueFamilies(String uniqueFamilies) {
		UniqueFamilies = uniqueFamilies;
	}

	public String getNumOfMatchedFamilies() {
		return NumOfMatchedFamilies;
	}

	public void setNumOfMatchedFamilies(String numOfMatchedFamilies) {
		NumOfMatchedFamilies = numOfMatchedFamilies;
	}

	public String getNumOfExpectedFamilies() {
		return NumOfExpectedFamilies;
	}

	public void setNumOfExpectedFamilies(String numOfExpectedFamilies) {
		NumOfExpectedFamilies = numOfExpectedFamilies;
	}


}
