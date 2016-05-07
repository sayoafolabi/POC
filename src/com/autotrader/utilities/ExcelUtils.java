package com.autotrader.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils 
{
	private static XSSFSheet ExcelWorkSheet;
	private static XSSFWorkbook ExcelWorkBook;
	private static XSSFCell Cell;
	private static FileInputStream ExcelFile;
	
	
	public static void setExcelFile(String Path) throws Exception
	{
		try
		{
			ExcelFile = new FileInputStream(Path);
			ExcelWorkBook = new XSSFWorkbook(ExcelFile);
			ExcelWorkSheet = ExcelWorkBook.getSheetAt(0);
					
					
		} catch(Exception e)
		{
			throw (e);
		}
	}
	
	public static Integer LastRowNum(String Path) throws Exception
	{
		try
		{
			ExcelFile = new FileInputStream(Path);
			
			ExcelWorkBook = new XSSFWorkbook(ExcelFile);
			ExcelWorkSheet = ExcelWorkBook.getSheetAt(0);
			
			return ExcelWorkSheet.getLastRowNum();
			
		} catch(Exception e)
		{
			throw (e);
		}
	}
	
	public static String getCellData(int RowNumber, int ColNumber) throws Exception
	{
		try
		{
			Cell = ExcelWorkSheet.getRow(RowNumber).getCell(ColNumber);
			return Cell.getStringCellValue();
		} catch (Exception e)
		{
			throw (e);
		}
	}
	
	public static void closeWorkBook() throws Exception
	{
		ExcelWorkBook.close();
		ExcelFile.close();
	}
	
}
