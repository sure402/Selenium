package com.suresh.utils;
import java.util.Hashtable;

/*This will help us to get data on a particular column during runtime*/

public class DataUtil {

	public static Object[][] getData(String sheetName, String path) throws Exception {

		System.out.println("Getting the Data from the Excel sheet and storing into Object Array");
		TestUtil.setExcelFile(path, sheetName);
		Object testData[][] = new Object[TestUtil.getRowCount(sheetName) - 1][1];
		Hashtable<String, String> table = null;
		int index = 0;

		for (int rowNum = 1; rowNum < TestUtil.getRowCount(sheetName); rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < TestUtil.getColumnCount(sheetName); colNum++) {
				String key = TestUtil.getCellData(1, colNum, sheetName);
				String value = TestUtil.getCellData(rowNum + 1, colNum, sheetName);
				table.put(key, value);
				testData[index][0] = table;
			}
			index++;
		}

		return testData;
	}
}