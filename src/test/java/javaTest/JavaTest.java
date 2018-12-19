package javaTest;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class JavaTest {
	
	public ExcelOperation excelOp;

	
	public static void main(String args[]) throws IOException {
		String filePath = "C:\\API-Test-Excel";
		String fileName = "Test-Api.xls";
		String sheetName = "Sheet1";
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/firstReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		ExcelOperation.readExcel(filePath, fileName, sheetName, extent);
		
	}

		

}
