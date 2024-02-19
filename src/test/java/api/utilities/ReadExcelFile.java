package api.utilities ;

import java.io.FileInputStream ;

import org.apache.poi.xssf.usermodel.XSSFCell ;
import org.apache.poi.xssf.usermodel.XSSFRow ;
import org.apache.poi.xssf.usermodel.XSSFSheet ;
import org.apache.poi.xssf.usermodel.XSSFWorkbook ;

public class ReadExcelFile
{

	public static FileInputStream inputStream ;
	public static XSSFWorkbook excelWorkbook ;
	public static XSSFSheet excelSheet ;
	public static XSSFRow excelRow ;
	public static XSSFCell excelColumn ;

	public static String getCellValue (String fileName , String sheetName , int rowNum , int columnNum)
	{
		try
		{
			inputStream = new FileInputStream (fileName) ;
			excelWorkbook = new XSSFWorkbook (inputStream) ;
			excelSheet = excelWorkbook.getSheet(sheetName) ;
			excelColumn = excelWorkbook.getSheet(sheetName).getRow(rowNum).getCell(columnNum) ;
			excelWorkbook.close() ;
			return excelColumn.getStringCellValue() ; 
		}
		catch (Exception e) 
		{
			return "" ; 
		}
	}

	public static int getRowCount (String fileName , String sheetName)
	{
		try
		{	
			inputStream = new FileInputStream (fileName) ;	
			excelWorkbook = new XSSFWorkbook (inputStream) ;	
			excelSheet = excelWorkbook.getSheet(sheetName) ;	
			int totalRows = excelSheet.getLastRowNum() + 1 ; 
			excelWorkbook.close() ;	
			return 	totalRows ; 
		}
		catch (Exception e)
		{
			return 0 ;
		}
	}

	public static int getColumnCount (String fileName , String sheetName)
	{	
		try
		{	
			inputStream = new FileInputStream (fileName) ;	
			excelWorkbook = new XSSFWorkbook (inputStream) ;	
			excelSheet = excelWorkbook.getSheet(sheetName) ;	
			int totalColumns = excelSheet.getRow(0).getLastCellNum() ;
			excelWorkbook.close() ;	
			return 	totalColumns ; 
		}
		catch (Exception e) 
		{
			return 0 ;
		}
	}

}
