package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import CommonFunctions.FunctionLibrary;
import Config.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath="F:\\secondclass\\Hybrid_Framework\\TestInput\\DataEngine.xlsx";
	String Outpath="F:\\secondclass\\Hybrid_Framework\\TestOutput\\PrimusResults.xlsx";
	String TCCases="TestCases";
	String TSSheet="TestSteps";
	@Test
	public void startest()throws Throwable{
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);
		//get row count
		int TCcount=xl.getRow(TCCases);
		int TScount=xl.getRow(TSSheet);
		Reporter.log(TCcount+"  "+TScount);
		//iterate all rows from TC
		for (int i = 1; i <=TCcount ; i++) { 
			//read executionmode cell
			String Executionmode=xl.getcelldata(TCCases, i, 2);
			if (Executionmode.equalsIgnoreCase("Y")) {
				String tcid=xl.getcelldata(TCCases, i, 0);
				//iterate all rows from tssheet
				for (int j = 1; j <= TScount; j++) {
					//read id cell
					String tsid=xl.getcelldata(TSSheet, j, 0);
					if (tcid.equalsIgnoreCase(tsid)) {
						//read keyword
						String keyword=xl.getcelldata(TSSheet, j, 3);
						
						if (keyword.equalsIgnoreCase("AdminLogin")) {
							String para1=xl.getcelldata(TSSheet, j, 5);
							String para2=xl.getcelldata(TSSheet, j, 6);
							res=FunctionLibrary.verifylogin(para1, para2);
							
							
						}
						else if (keyword.equalsIgnoreCase("NewBranch")) {
							String para1=xl.getcelldata(TSSheet, j, 5);

							String para2=xl.getcelldata(TSSheet, j, 6);
							String para3=xl.getcelldata(TSSheet, j, 7);
							String para4=xl.getcelldata(TSSheet, j, 8);
							String para5=xl.getcelldata(TSSheet, j, 9);
							String para6=xl.getcelldata(TSSheet, j, 10);
							String para7=xl.getcelldata(TSSheet, j, 11);
							String para8=xl.getcelldata(TSSheet, j, 12);
							String para9=xl.getcelldata(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res=FunctionLibrary.verifyNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							
							
						}
						else if (keyword.equalsIgnoreCase("UpdateBranch")) {
							String para1=xl.getcelldata(TSSheet, j, 5);
							String para2=xl.getcelldata(TSSheet, j, 6);
							String para3=xl.getcelldata(TSSheet, j, 9);
							String para4=xl.getcelldata(TSSheet, j, 10);
							FunctionLibrary.clickBranches();
							res=FunctionLibrary.VerifyBranchUpdate(para1, para2, para3, para4);	
						}
						else if (keyword.equalsIgnoreCase("AdminLogout")) {
							res= FunctionLibrary.VerifyLogout();
							}
						String tsres="";
						if (res) {
							//if res is true write as pass
							tsres="pass";
							xl.SetCellValue(TSSheet, j, 4, tsres, Outpath);
							
						}
						else {
							tsres="fail";
							xl.SetCellValue(TSSheet, j, 4, tsres, Outpath);
						}
						tcres=tsres;	
					}					
				}
				//write tcres into tcsheet
				xl.SetCellValue(TCCases, i, 3, tcres, Outpath);
				
			}
			else {
				//write as blocked into status cell in tcsheet for flag N
				xl.SetCellValue(TCCases, i, 3, "Blocked", Outpath);
			}		
		}	
	}
}
