package com.krishna.service.model;

/**
 * Class to manage Response POJO's. Since we don't have a way to map developer POJO's Hence created custom POJO's
 * 
 * @author kabothu 
 */

public class ResponsePojoBuilder {

	private Results[] results;

	public Results[] getResults() {

		return results;

	}

	public void setResults(Results[] results) {

		this.results = results;

	}

	@Override
	public String toString() {

		return "ResponsePojoBuilder [results = " + results + "]";

	}

}