package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class LoginPage extends BasePage{
	ActionKeyword actions;
	private WebElement txtusername,txtpassword,btnLogin;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
	}

	public InventoryPage loginToInventoryPage(String username, String password) throws Exception {
		txtusername = actions.waitElementVisible("xpath;//*[contains(@id,'user-name')]");
		actions.sendText(txtusername, username);
		
		txtpassword = actions.waitElementVisible("xpath;//*[contains(@id,'password')]");
		actions.sendText(txtpassword, password);
		
		btnLogin = actions.waitElementVisible("xpath;//*[contains(@id,'login-button')]");
		actions.click(btnLogin);
		
		return new InventoryPage(this.driver);
	}
}

