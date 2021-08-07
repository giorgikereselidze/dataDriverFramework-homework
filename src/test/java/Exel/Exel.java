package Exel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class Exel {
    @Test
    public void readExcel(String filePath,String fileName,String sheetName) throws IOException {
        File file =    new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx")){
            workbook = new XSSFWorkbook(inputStream);

        }
        else if(fileExtensionName.equals(".xls")){
            workbook = new HSSFWorkbook(inputStream);

        }

        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
//
//        for (int i = 0; i < rowCount+1; i++) {
//
//
//            Row row = sheet.getRow(i);
//
//            //Create a loop to print cell values in a row
//
//            for (int j = 0; j < row.getLastCellNum(); j++) {
//
//                //Print Excel data in console
//
//
//                open("https://demoqa.com/automation-practice-form");
//                $("#firstName").setValue(sheet.getRow(i).getCell(j).getStringCellValue());
//                $("#lastName").setValue(sheet.getRow(i).getCell(j).getStringCellValue());
//                $(By.xpath("//*[@for='gender-radio-1']")).click();
//                $("#userNumber").setValue(String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue()));
//
//
//            }
//        }


        open("https://demoqa.com/automation-practice-form");
                $("#firstName").setValue(sheet.getRow(0).getCell(0).getStringCellValue());
                $("#lastName").setValue(sheet.getRow(0).getCell(1).getStringCellValue());
                $(By.xpath("//*[@for='gender-radio-1']")).click();
                $("#userNumber").setValue(String.valueOf(sheet.getRow(0).getCell(3).getNumericCellValue()));



        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(sheet.getRow(1).getCell(0).getStringCellValue());
        $("#lastName").setValue(sheet.getRow(1).getCell(1).getStringCellValue());
        $(By.xpath("//*[@for='gender-radio-1']")).click();
        $("#userNumber").setValue(String.valueOf(sheet.getRow(1).getCell(3).getNumericCellValue()));



        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(sheet.getRow(2).getCell(0).getStringCellValue());
        $("#lastName").setValue(sheet.getRow(2).getCell(1).getStringCellValue());
        $(By.xpath("//*[@for='gender-radio-2']")).click();
        $("#userNumber").setValue(String.valueOf(sheet.getRow(2).getCell(3).getNumericCellValue()));


    }


    public static void main(String...strings) throws IOException{
        Exel objExcelFile = new Exel();

        String filePath = "src/main/resources";

        objExcelFile.readExcel(filePath,"Test.xlsx","Sheet1");

    }

}
