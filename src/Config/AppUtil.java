package Config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver con;
	public static Properties p;
	@BeforeSuite
	public static void setup() throws Throwable{
		p=new Properties();
		p.load(new FileInputStream("F:\\secondclass\\Hybrid_Framework\\PropertyFile\\Envirnoment.properties"));
		if (p.getProperty("Browser").equalsIgnoreCase("chrome")) {
			con=new ChromeDriver();
			con.manage().window().maximize();
			con.manage().deleteAllCookies();
			con.get(p.getProperty("Url"));
			con.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}else if (p.getProperty("Browser").equalsIgnoreCase("firefox")) {
			con= new FirefoxDriver();
			con.get(p.getProperty("Url"));
			con.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
		else {
			System.out.println("browser value not matching: ");
		}
	}
	@AfterSuite
	public static void teardown()throws Throwable{
		con.close();
	}
	
	

}
