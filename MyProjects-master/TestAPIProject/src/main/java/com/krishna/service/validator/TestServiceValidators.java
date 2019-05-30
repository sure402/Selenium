package com.krishna.service.validator;

import org.testng.asserts.Assertion;
import com.google.gson.Gson;
import com.krishna.service.model.ResponsePojoBuilder;
import com.krishna.service.model.Results;
import com.krishna.service.model.TestService;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Class to manage Validations.We may not need this file. We make the validations more readable and re-usable separated
 * them into this file so that maintenance will be easier.
 * 
 * @author kabothu 
 */

public class TestServiceValidators {

	public static class Validator {

		ClientResponse response;
		Assertion assertChain;
		ResponsePojoBuilder responsePojo;
		TestService testService;

		public Validator(ClientResponse response) {
			assertChain = new Assertion();
			this.response = response;
		}

		// Validates the response code
		public Validator responseCode(String responseCode) {
			assertChain.assertEquals(responseCode, String.valueOf(response.getStatus()),
					"Expected response is not matching with actual Response");
			return this;
		}

		// Validates results count from response
		public Validator validateResultsCount(TestService testService, String responseBody) {
			int count;
			ResponsePojoBuilder results = new Gson().fromJson(responseBody, ResponsePojoBuilder.class);
			int resultsCount = results.getResults().length;
			try {
				count = Integer.parseInt(testService.getCount());
				//To Check if the count is greater than 0
				if (count > 0) {
					assertChain.assertEquals(resultsCount, testService.getCount(),
							"Result count from the response is not matching with the count from params");
				//To check if the count is equal to 0
				} else if(count == 0) {
					assertChain.assertEquals(resultsCount, 16,
							"Service is not returning all the results");
				//To check if the count is less than 0
				}else {
					assertChain.fail("count values are negative but still we got the response from service");
				}
			} catch (IllegalArgumentException e) {
				assertChain.fail("Deserialization of response is not working");
			}
			return this;
		}
		
		// Validate Post Call Details
		public Validator validPostCallDetails(TestService testService, String responseBody) {
			ResponsePojoBuilder results = new Gson().fromJson(responseBody, ResponsePojoBuilder.class);
			String expectedTitle = testService.getTitle();
			Results[] resultsArray = results.getResults();
			boolean titleFlag = false;
			for (int i = 0; i <= resultsArray.length-1; i++) {
				titleFlag = resultsArray[i].getTitle().equals(expectedTitle);
				if (titleFlag) {
					assertChain.assertEquals(resultsArray[i].getTitle(), expectedTitle,
							"Both the titles were not matching");
				}else {
					assertChain.fail("Post call movie information is not present in Get call");
				}
			}

			return this;
		}
	}
}