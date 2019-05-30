package com.suresh.test;

import java.util.Map;

import org.testng.annotations.Test;

import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.suresh.utils.BeanClass;

public class ExcelDataProviderTestUsingThirdPartyLibrary {

	@QAFDataProvider(dataFile = "src/test/resources/excelfile.xls", sheetName = "excelreader")
	@Test
	public void testDataProviderUsingMapWithThirdParty(Map<String, Object> data) {

		System.out.println(data.get("name"));
	}

	
	@QAFDataProvider(dataFile = "src/test/resources/excelfile.xls", sheetName = "excelreader")
	@Test
	public void testWithBeanClass(BeanClass data) {

		System.out.println(data.getName());
	}
}
