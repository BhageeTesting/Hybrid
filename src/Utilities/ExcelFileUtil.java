package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//create constructor
	public ExcelFileUtil(String ExcelPath) throws Throwable {
		FileInputStream fi= new FileInputStream(ExcelPath);
		wb=new XSSFWorkbook(fi);
		
		
	}
	//count no of rows 
	public int getRow(String SheetName)  {
		return wb.getSheet(SheetName).getLastRowNum(); 
		
		
	}
	//count no of coloumns in row
	public int getcoloumn(String SheetName) {
		return wb.getSheet(SheetName).getRow(0).getLastCellNum();
	}
	//read cell data
	public String getcelldata(String SheetName,int row,int coloumn) {
		String data="";
		if (wb.getSheet(SheetName).getRow(row).getCell(coloumn).getCellType()==Cell.CELL_TYPE_NUMERIC) 
		{
			int celldata=(int)wb.getSheet(SheetName).getRow(row).getCell(coloumn).getNumericCellValue();
		data=String.valueOf(celldata);
			
			
		}
		else {
			data=wb.getSheet(SheetName).getRow(row).getCell(coloumn).getStringCellValue();
			
		}
		return data;
	}
	//method for writing
	public void SetCellValue(String SheetName,int row,int coloumn,String status,String writeExcelPath  ) throws Throwable {
	//get sheet
		XSSFSheet ws=wb.getSheet(SheetName);
		//get row
		XSSFRow r=ws.getRow(row);
		//get cell
		XSSFCell cell=r.createCell(coloumn);
		//write cell
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloumn).setCellStyle(style);
			}
		else if (status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloumn).setCellStyle(style);
			
		}
		else if (status.equalsIgnoreCase("blocked")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE_GREY.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloumn).setCellStyle(style);
			
				}
			FileOutputStream fo= new FileOutputStream(writeExcelPath);
		wb.write(fo);
		//return status;		
	}
public static void main(String[] args) throws Throwable{
		ExcelFileUtil xl= new ExcelFileUtil("F://Sai.xlsx");
		int rc= xl.getRow("Login");
		int cc= xl.getcoloumn("Login");
		System.out.println(rc+"   "+cc);
		for (int i = 1; i <= rc; i++) {
			String user=xl.getcelldata("Login", i, 0);
			String pass=xl.getcelldata("Login", i, 1);
			System.out.println(user+"    "+pass);
			//xl.SetCellValue("Login", i, 2, "Pass","F://Results.xlsx");
			//xl.SetCellValue("Login", i, 2, "fail", "F://Results.xlsx");
			xl.SetCellValue("Login", i, 2, "blocked", "F://Results.xlsx");
			
		}
		
	}
	}


