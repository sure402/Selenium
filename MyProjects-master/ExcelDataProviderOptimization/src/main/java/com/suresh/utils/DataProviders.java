package com.suresh.utils;
import org.testng.annotations.DataProvider;

public class DataProviders {
	

	@DataProvider(name = "hashTableExample")
	public static Object[][] testData() throws Exception {
		String filePath = "src/test/resources/excelfile.xls";
		return DataUtil.getData("excelreader", filePath);
	}
	
	@DataProvider(name = "usingAllArguments")
	public static Object[][] testData1() throws Exception {
		String filePath = "src/test/resources/excelfile.xls";
		return new ExcelUtilsCommon().getExcelData(filePath, "excelreader");
	}
}
