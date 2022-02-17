package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver getDriver(String browser) {
		DesiredCapabilities desiredCap = new DesiredCapabilities();
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();	
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			desiredCap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			return new ChromeDriver(chromeOptions);
		case "firefox":
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--start-maximized");
            desiredCap.setCapability("moz:firefoxOptions", firefoxOptions);
            return new FirefoxDriver(firefoxOptions);
        default:
            return new ChromeDriver(); 
		}
	}
}
