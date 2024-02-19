package api.utilities ;

import org.testng.annotations.DataProvider ;

public class DataProviders
{

	@DataProvider (name = "testDataProviderAuthentication")	
	public String [][] dataProviderAuthentication ()
	{
		String fileName = System.getProperty("user.dir") + "//test-data//api_test_data.xlsx" ;
		int totalRowCount = ReadExcelFile.getRowCount(fileName , "AUTHORIZATION") ;
		int totalColumnCount = ReadExcelFile.getColumnCount(fileName , "AUTHORIZATION") ;
		String testDataArrayAuthentication[][] = new String [totalRowCount - 1][totalColumnCount] ;
		for (int rowNumber = 1 ; rowNumber < totalRowCount ; rowNumber++)
		{
			for (int columnNumber = 0 ; columnNumber < totalColumnCount ; columnNumber++)
			{
				testDataArrayAuthentication [rowNumber - 1][columnNumber] = ReadExcelFile.getCellValue (fileName , "AUTHORIZATION" , rowNumber , columnNumber) ; 
			}
		}
		return 	testDataArrayAuthentication ; 
	}

	@DataProvider (name = "testDataProviderProjectId")	
	public String [] dataProviderProjectId () 
	{
		String fileName = System.getProperty("user.dir") + "//test-data//api_test_data.xlsx" ;
		int totalRowCount = ReadExcelFile.getRowCount(fileName , "PROJECT_ID") ;
		String testDataArrayProjectId[] = new String [totalRowCount - 1] ;
		for (int rowNumber = 1 ; rowNumber < totalRowCount ; rowNumber++)
		{
			testDataArrayProjectId [rowNumber - 1] = ReadExcelFile.getCellValue (fileName , "PROJECT_ID" , rowNumber , 0) ; 
		}
		return 	testDataArrayProjectId ; 
	}

}
