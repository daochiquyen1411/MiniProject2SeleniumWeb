package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.management.OperationsException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionKeyword {
	public WebDriver driver;
	public Actions action;
	public WebDriverWait wait;
	public JavascriptExecutor executor;
	
	
	public ActionKeyword(WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigate(String url) throws Exception {
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new Exception("Invalid url format");
		}
		
		try {
			this.driver.get(url);
		}catch (WebDriverException e) {
			this.errorSnapshot();
			throw new Exception ("Get " + e.getMessage() + ", cant navigate to " + url);
		}
		
		
	}	
	
	public void OpenUrl(String url) throws Exception {
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new Exception("Invalid url format");
		}
		
		try {
			this.driver.get(url);
		}catch (WebDriverException e) {
			this.errorSnapshot();
			throw new Exception ("Get " + e.getMessage() + ", cant open url " + url);
		}
	}	
	
	public void errorSnapshot() throws Exception {		
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());				
		File file = new File("src/test/java/TestSnapshot/Error" + date + ".png");
		boolean result;  
		
		try {
			result = file.createNewFile(); 
		}catch (IOException e) {
			e.printStackTrace();
		}

		this.takeSnapShot("src/test/java/TestSnapshot/Error" + date + ".png");
	}
	
		
	public void click(WebElement element) throws Exception {		
		action = new Actions(this.driver);	
		action.moveToElement(element).build().perform();
		try {
			element.click();
		}catch (WebDriverException e) {			
			this.errorSnapshot();
			e.printStackTrace();
		}

	}
	
	public void takeSnapShot(String fileWithPath) throws Exception{
		try {
			//Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot =((TakesScreenshot)this.driver);
			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			//Move image file to new destination
			File DestFile=new File(fileWithPath);
			//Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendText(WebElement element, String text) throws Exception {
		try {
			element.clear();
			element.sendKeys(text);
		}catch (WebDriverException e){
			throw new Exception ("Get " + e.getMessage() + ", " + element + " is not ready for send keys");
		}
	}
	
	public void clearText(WebElement element) {
		element.clear();
	}
	
	public void waitForPageToLoad(WebDriver driver) throws Exception {
		try {
			new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));
		} catch (InvalidOperationException e) {
			this.errorSnapshot();
			System.out.println(e.getMessage().toLowerCase().contains("unable to Driver browser"));
		} catch (WebDriverException e) {
			this.errorSnapshot();
			System.out.println(e.getMessage().toLowerCase().contains("unable to connect"));
		} catch (Exception e) {
			this.errorSnapshot();
			throw (e);
		}
	}
	
	public void waitTitleContains(String title, int timeout) throws OperationsException {
		try {
			wait = new WebDriverWait(this.driver,timeout);
			wait.until(ExpectedConditions.titleContains(title));
		}catch (WebDriverException e){
			throw new OperationsException("Get " + e.getMessage() + ",[" + title + "] is not display in WebPage title [" + this.driver.getTitle() + "]");
		}
	}
	
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	public String getAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	public String getCssValue(WebElement element, String value) {
		return element.getCssValue(value);
	}
	
	public String GetPageSource()
    {
        return this.driver.getPageSource();
    }
	
	
	
	public WebElement waitElementToBeClickable(String value) throws Exception {
		WebElement element = null;
		String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];
		
		switch (locatorType.toLowerCase()) {		
		case "xpath":
			try {
				wait = new WebDriverWait(this.driver,10);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			}catch (WebDriverException e){
				this.errorSnapshot();
				throw new OperationsException("Get " + e.getMessage() + ", " + value + "is not ready for clickable");
			}
			break;
		case "css":	
			try {
				wait = new WebDriverWait(this.driver,10);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			}catch (WebDriverException e){
				this.errorSnapshot();
				throw new OperationsException("Get " + e.getMessage() + ", " + value + "is not ready for clickable");
			}
			break;
		}
		
		return element;
		
	}
	
	public void switchToFrame(WebElement iframe) {
		this.driver.switchTo().frame(iframe);
	}
	
	public void switchToDefaultContent() {
		this.driver.switchTo().defaultContent();
	}
	
	
	public WebElement waitElementVisible(String value) throws Exception {
		WebElement element = null;
		String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];
		
		switch (locatorType.toLowerCase()) {		
		case "xpath":
			try {
				wait = new WebDriverWait(this.driver,10);
				element = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(locatorValue))));
			}catch (WebDriverException e){
				this.errorSnapshot();
				throw new OperationsException("Get " + e.getMessage() + ", " + value + "is not ready for clickable");
			}
			break;
		case "css":	
			try {
				wait = new WebDriverWait(this.driver,10);
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			}catch (WebDriverException e){
				this.errorSnapshot();
				throw new OperationsException("Get " + e.getMessage() + ", " + value + "is not ready for clickable");
			}
			break;
		}
		
		return element;
	
	}
	
	public void moveToElementPointer(WebElement element) {		
		action = new Actions(this.driver);
		action.moveToElement(element).build().perform();
		
	}
	
	public WebElement scrollToElementUsingJs(WebElement element) {
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public void scrollPageUsingJs(int value) {
		 JavascriptExecutor js = (JavascriptExecutor) this.driver;
		 js.executeScript("window.scrollBy(0," + value + ")");
	}
	
	public void clickWithJs(WebElement element) {
		executor = (JavascriptExecutor)this.driver;
		executor.executeScript("arguments[0].click();", element);
	}
		
	public WebElement findElement(String value) throws Exception {
		WebElement element = null;
		String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];
		switch (locatorType.toLowerCase()) {
		case "id":			
			element = driver.findElement(By.id(locatorValue));
			break;
		case "name":
			element = driver.findElement(By.name(locatorValue));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(locatorValue));
			break;
		case "tag":
			element = driver.findElement(By.tagName(locatorValue));
		case "link":
			element = driver.findElement(By.linkText(locatorValue));
			break;
		case "css":
			element = driver.findElement(By.cssSelector(locatorValue));
			break;
		case "class":
			element = driver.findElement(By.className(locatorValue));
			break;
		default:
			throw new Exception("Support FindElement with 'id' 'name' 'xpath' 'tag' 'link' 'css' 'class'");
		}
		return element;
	}
	
	public List<WebElement> FindElements(String value) throws Exception
    {
        List<WebElement> elements = null;
        String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];
        switch (locatorType.toLowerCase())
        {
	        case "id":				
				elements = driver.findElements(By.id(locatorValue));
				break;
			case "name":
				elements = driver.findElements(By.name(locatorValue));
				break;
			case "xpath":
				elements = driver.findElements(By.xpath(locatorValue));
				break;
			case "tag":
				elements = driver.findElements(By.tagName(locatorValue));
			case "link":
				elements = driver.findElements(By.linkText(locatorValue));
				break;
			case "css":
				elements = driver.findElements(By.cssSelector(locatorValue));
				break;
			case "class":
				elements = driver.findElements(By.className(locatorValue));
				break;
            default:
                throw new Exception("Support FindElement with 'id' 'name' 'xpath' 'tag' 'link' 'css' 'class'");
        }
        return elements;
    }
}

