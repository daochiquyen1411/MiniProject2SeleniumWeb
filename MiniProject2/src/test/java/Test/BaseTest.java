package Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utilities.Configuration;
import Utilities.DriverFactory;
import Utilities.ActionKeyword;

public class BaseTest {
	 public WebDriver driver;
	 public WebDriverWait wait;
	 public Actions action;
	 
	 @BeforeMethod
	 public void setUp() throws Exception{
		  Configuration configuration = new Configuration();
		     
		  Properties prop = configuration.loadConfigPropertiesFile("src/main/resources/config.properties");
		     
		  this.driver = DriverFactory.getDriver(prop.getProperty("browser"));	     
		     
		  ActionKeyword actionKeyword = new ActionKeyword(this.driver);
		     
		  actionKeyword.navigate(prop.getProperty("url"));
	 }
	 
	 @AfterMethod
		public void tearDown(){
			
		    this.driver.quit();
		    
		}
}
