package com.krishna.service.model;

/**
 * Class to manage Input Data in the form of different Objects. We pass this to the data provider.
 * 
 * @author kabothu 
 */

public class TestServiceMock {

	private TestService[] testService;
	private TestService[] testServiceWithCount;
	private TestService[] testServiceWithOutParams;
	private TestService[] testPostService;

	public TestService[] getTestService() {
		return testService;
	}

	public void setTestService(TestService[] testService) {
		this.testService = testService;
	}

	public TestService[] getTestServiceWithCount() {
		return testServiceWithCount;
	}

	public void setTestServiceWithCount(TestService[] testServiceWithCount) {
		this.testServiceWithCount = testServiceWithCount;
	}

	public TestService[] getTestServiceWithOutParams() {
		return testServiceWithOutParams;
	}

	public void setTestServiceWithOutParams(TestService[] testServiceWithOutParams) {
		this.testServiceWithOutParams = testServiceWithOutParams;
	}

	public TestService[] getTestPostService() {
		return testPostService;
	}

	public void setTestPostService(TestService[] testPostService) {
		this.testPostService = testPostService;
	}

	@Override
	public String toString() {
		return "ClassPojo [testService = " + testService + "]";
	}
}