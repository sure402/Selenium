package com.suresh.test;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.suresh.utils.DataProviders;

public class ExcelDataProviderTestWithHashTable {
	
	@Test(dataProvider = "hashTableExample", dataProviderClass = DataProviders.class)
	public void testDataProviderUsingHashTableExample(Hashtable<String, String> data) {

		System.out.println(data.get("name"));
	}

	@Test(dataProvider = "usingAllArguments", dataProviderClass = DataProviders.class)
	public void testDataProviderUsingAllArgumentsExample(String name, String address, String email) {

		System.out.println(name);
	}
}
