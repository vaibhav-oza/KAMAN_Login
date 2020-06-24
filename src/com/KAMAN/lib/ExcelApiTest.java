package com.KAMAN.lib;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelApiTest {
	public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    public int rowCount;
    public int colCount;
    String xlFilePath;
    DataFormatter df;
    
    public int RowCount(int sheetIndex) throws Exception {
		 sheet = workbook.getSheetAt(sheetIndex);
		 rowCount= sheet.getPhysicalNumberOfRows();
		 return rowCount;
	 }
	
	 public int ColCount(int sheetIndex) throws Exception {
		 sheet = workbook.getSheetAt(sheetIndex);
		 colCount= sheet.getRow(0).getPhysicalNumberOfCells();
		 return colCount;
	 }
	 
	 public String getData(int row,int column,int sheetfromfile) {
			
		 
			df = new DataFormatter();
			sheet = workbook.getSheetAt(sheetfromfile);
			//System.out.println("before");
			cell = sheet.getRow(row).getCell(column);
			//System.out.println("after");
			//String data ="s";
			String data = df.formatCellValue(cell);
			return data;	
		}
	 
	    public ExcelApiTest(String xlFilePath) throws Exception
	    {
	        this.xlFilePath = xlFilePath;
	        fis = new FileInputStream(xlFilePath);
	        workbook = new XSSFWorkbook(fis);
	        //fis.close();
	    }
	}

