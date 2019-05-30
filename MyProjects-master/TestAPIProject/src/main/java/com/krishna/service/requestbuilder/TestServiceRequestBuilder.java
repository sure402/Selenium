package com.krishna.service.requestbuilder;

import com.sun.jersey.api.client.Client;

/**
 * Class to manage RequestBuilder
 * 
 * @author kabothu 
 */

public class TestServiceRequestBuilder {

	public String CONTENT_TYPE = "application/json";
	private Client client;
	private String url;

	public TestServiceRequestBuilder(String url) {
		this.url = url;
		this.client = new Client();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
