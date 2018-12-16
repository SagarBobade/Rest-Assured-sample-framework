package javaTest;

import java.io.IOException;

import org.testng.annotations.Test;

// framework to test whether any api call fail to response for what it is made.
public class JavaTest {
	
	public ExcelOperation excelOp;

	//TestNg will analyze for this annotation and execute script from here
	@Test
	public void testResponseCode() throws IOException {

		//initializing imp variables
		String filePath = "C:\\API-Test-Excel";
		String fileName = "Test-Api.xls";
		String sheetName = "Sheet1";
		
		ExcelOperation.readExcel(filePath, fileName, sheetName);

	}
}
