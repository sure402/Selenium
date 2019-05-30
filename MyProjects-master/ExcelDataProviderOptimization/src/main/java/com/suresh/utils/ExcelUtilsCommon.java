package com.suresh.utils;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*This will help us to get pass all the columns as arguments to test method*/

public class ExcelUtilsCommon {
	private static Sheet sheet;
	private static Cell cell;

	/**
	 * @param fileName
	 * @param sheetName
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) {

		String[][] arrayExcelData = null;
		try {

			FileInputStream inputStream = new FileInputStream(fileName);
			Workbook workbook = null;
			int rowIndex, columnIndex;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
			}
			sheet = workbook.getSheet(sheetName);
			int totalNoOfCols = sheet.getRow(0).getLastCellNum();
			int totalRowCount = sheet.getLastRowNum();
			arrayExcelData = new String[totalRowCount][totalNoOfCols];
			rowIndex = 0;
			for (int i = 1; i <= totalRowCount; i++, rowIndex++) {
				columnIndex = 0;
				for (int j = 0; j < totalNoOfCols; j++, columnIndex++) {
					arrayExcelData[rowIndex][columnIndex] = getCellData(i, j);
				}

			}

		} catch (Exception e) {
			e.getMessage();
		}

		return arrayExcelData;
	}

	/**
	 * @param RowNum
	 * @param ColNum
	 * @return
	 */
	public static String getCellData(int RowNum, int ColNum) {

		try {

			cell = sheet.getRow(RowNum).getCell(ColNum);

			int dataType = cell.getCellType();

			if (dataType == 3) {

				return "";

			} else {

				String CellData = cell.getStringCellValue();

				return CellData;
			}

		} catch (Exception e) {

			return e.getMessage();

		}
	}
}
