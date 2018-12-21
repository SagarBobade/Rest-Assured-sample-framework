package javaTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.aventstack.extentreports.ExtentReports;

import io.restassured.response.Response;

public class ExcelOperation {

	public static void readExcel(String filePath, String fileName, String sheetName, ExtentReports extent)
			throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		String reqUrl = null;
		String method = null;
		Workbook workbook = null;
		Response resp;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

			int currentBody = 6;
			System.out.println("******************Total APIs are : " + sheet.getLastRowNum() + " ******************");
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				reqUrl = sheet.getRow(i).getCell(1).toString();
				method = sheet.getRow(i).getCell(2).toString();

				System.out.println("Processing " + i + ") " + reqUrl + " " + method);
				currentBody = 6;
				while (currentBody <= sheet.getRow(i).getLastCellNum()-1) {
						System.out.println("last cell in "+i+": row "+sheet.getRow(i).getLastCellNum());
					if (sendRequest.testResponseCode(reqUrl, sheet.getRow(i).getCell(2).toString(),
							Double.parseDouble(sheet.getRow(i).getCell(3).toString()),
							sheet.getRow(i).getCell(currentBody).toString(),
							Integer.valueOf(
									(int) Math.round(Double.parseDouble(sheet.getRow(i).getCell(4).toString()))),
							extent) == 0) {
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCell cell = sheet.getRow(i).createCell(5);
						cell.setCellValue("PASS");
						System.out.println("PASS");
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
					} else {
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCell cell = sheet.getRow(i).createCell(5);
						System.out.println("FAIL");
						cell.setCellValue("FAIL");
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
					}
					currentBody++;

				}

			}
			extent.flush();
		}
	}
}
