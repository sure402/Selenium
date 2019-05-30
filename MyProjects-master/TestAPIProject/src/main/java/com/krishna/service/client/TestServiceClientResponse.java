package com.krishna.service.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import com.krishna.service.requestbuilder.TestServiceRequestBuilder;
import com.krishna.services.util.LoggerHelper;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Class to get all the different httpRequests
 *
 * @author kabothu 
 */

public class TestServiceClientResponse {

	private Logger logger;
	ClientResponse response;

	public TestServiceClientResponse() {
		if (logger == null) {
			logger = LoggerHelper.getLogger(TestServiceClientResponse.class);
		}
	}

	public ClientResponse getResponse(TestServiceRequestBuilder request) {
		logger.info("Get http Request: " + request.getUrl());
		this.response = request.getClient().resource(request.getUrl()).accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return response;
	}

	public ClientResponse getPostResponse(String payload, TestServiceRequestBuilder request) {
		logger.info("Get http Request: " + request.getUrl());
		this.response = request.getClient().resource(request.getUrl()).header("Content-Type",MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, payload);
		return response;
	}

	public ClientResponse getPutResponse(String payload, TestServiceRequestBuilder request) {
		logger.info("Get http Request: " + request.getUrl());
		this.response = request.getClient().resource(request.getUrl()).accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, payload);
		return response;
	}

	public ClientResponse getDeleteResponse(TestServiceRequestBuilder request) {
		logger.info("Get http Request: " + request.getUrl());
		this.response = request.getClient().resource(request.getUrl()).accept(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);
		return response;
	}

	public void loggerInfo(MultivaluedMap<String, String> headers,int statusCode, String response) {
		logger.info("Response Headers: " + headers);
		logger.info("Response Status: " + statusCode);
		logger.info("Response Returned: " + response);
	}
}
