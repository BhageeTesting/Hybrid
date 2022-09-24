package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Config.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
	public static boolean verifylogin(String username, String password) throws Throwable {
	con.findElement(By.xpath(p.getProperty("User"))).sendKeys(username);
	con.findElement(By.xpath(p.getProperty("Pass"))).sendKeys(password);
	con.findElement(By.xpath(p.getProperty("ObjButton"))).click();
	
	String expected="adminflow";
	String actual=con.getCurrentUrl();
	if (actual.toLowerCase().contains(expected.toLowerCase())) {
		Reporter.log("login success: "+expected+"  "+actual,true);
		return true;
		}
	else {
		Reporter.log("Login Fail: "+expected+"  "+actual,true);
		return false;
	}
	

}
	//method for click branches
	public static void clickBranches() throws Throwable{
		con.findElement(By.xpath(p.getProperty("BranchBut"))).click();
		
}
	//method for branch creation
	public static boolean verifyNewBranch(String BranchName, String Address1,String Address2,String Address3,String Area,String Zipcode,String country,String State,String City)throws Throwable{
		con.findElement(By.xpath(p.getProperty("New"))).click();
		Thread.sleep(2000);
		con.findElement(By.xpath(p.getProperty("Branchda"))).sendKeys(BranchName);
		con.findElement(By.xpath(p.getProperty("Add1"))).sendKeys(Address1);
		con.findElement(By.xpath(p.getProperty("Add2"))).sendKeys(Address2);
		con.findElement(By.xpath(p.getProperty("Add3"))).sendKeys(Address3);
		con.findElement(By.xpath(p.getProperty("Are"))).sendKeys(Area);
		con.findElement(By.xpath(p.getProperty("zip"))).sendKeys(Zipcode);
	new Select(con.findElement(By.xpath(p.getProperty("con")))).selectByVisibleText(country); 
	new Select(con.findElement(By.xpath(p.getProperty("sta")))).selectByVisibleText(State);
	new Select(con.findElement(By.xpath(p.getProperty("cit")))).selectByVisibleText(City);
	con.findElement(By.xpath(p.getProperty("submit"))).click();
	Thread.sleep(2000);
	//capture alret message
	String ExpectedAlert= con.switchTo().alert().getText();
	//click ok to alert
	con.switchTo().alert().accept();
	String ActualAlert="New Branch with";
	if (ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase())) {
		Reporter.log(ExpectedAlert,true);
		return true;
		
		
	}else {
		Reporter.log("Unable to create new branch",true);
		return false;
		
	}
	
		
	}
	//method for branch updation
	public static boolean VerifyBranchUpdate(String Branch, String Address, String Area,String zip)throws Throwable{
		con.findElement(By.xpath(p.getProperty("Objedit"))).click();
		WebElement element1=con.findElement(By.xpath(p.getProperty("Objbraned")));
		element1.clear();
		element1.sendKeys(Branch);
		WebElement element2=con.findElement(By.xpath(p.getProperty("Objadded")));
		element1.clear();
		element1.sendKeys(Address);
		WebElement element3=con.findElement(By.xpath(p.getProperty("Objareed")));
		element1.clear();
		element1.sendKeys(Area);
		WebElement element4=con.findElement(By.xpath(p.getProperty("Objziped")));
		element1.clear();
		element1.sendKeys(zip);
		con.findElement(By.xpath(p.getProperty("update"))).click();
		String ExpectedUpdateAlert=con.switchTo().alert().getText();
		con.switchTo().alert().accept();
		String ActualUpdateAlert="Branch updated";
		if (ExpectedUpdateAlert.toLowerCase().contains(ActualUpdateAlert.toLowerCase())) {
			Reporter.log(ExpectedUpdateAlert,true);
			return true;			
		}
		else {
			Reporter.log("Branch update failed",true);
			return false;
		}
		
		
		
	}
	//method for clicking roles
	public static void clickroles()throws Throwable{
		con.findElement(By.xpath(p.getProperty("rolebtn"))).click();
		
	}
	
	//method for role creation
//	public static boolean verifyrolecreation()throws Throwable{
//		con.findElement(By.xpath(p.getProperty("newrole"))).click();
//		con.findElement(By.xpath(p.getProperty(""))).sendKeys("");
//		con.findElement(By.xpath(p.getProperty(""))).sendKeys("");
//		new Sele
//		
//		
//		
//	}
	
	
	
	
	
	//method for logout
	public static boolean VerifyLogout() throws Throwable{
		con.findElement(By.xpath(p.getProperty("Objlogout"))).click();
		if (con.findElement(By.xpath(p.getProperty("ObjButton"))).isDisplayed()) {
			Reporter.log("Logout success",true);
			return true;
		}
		else {
			Reporter.log("Logout fail",true);
			return false;
		}
	}
	
}
