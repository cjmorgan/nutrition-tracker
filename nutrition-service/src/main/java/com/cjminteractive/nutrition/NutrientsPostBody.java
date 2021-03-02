package com.cjminteractive.nutrition;

public class NutrientsPostBody {
	private String query;
	
	public NutrientsPostBody(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
