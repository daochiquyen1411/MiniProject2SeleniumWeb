package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class CheckOutStepOnePage extends BasePage{
	ActionKeyword actions;
	private WebElement txtFirstName, txtLastName, txtZipCode,btnContinue;
	public CheckOutStepOnePage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
		// TODO Auto-generated constructor stub
	}
	
	public CheckOutStepTwoPage toCheckOutStepTwoPage() throws Exception {
		txtFirstName = actions.waitElementVisible("css;#first-name");
		actions.sendText(txtFirstName, "Quyen");
		
		txtLastName = actions.waitElementVisible("css;#last-name");
		actions.sendText(txtLastName, "Dao");
		
		txtZipCode = actions.waitElementVisible("css;#postal-code");
		actions.sendText(txtZipCode, "70000");
				
		btnContinue = actions.waitElementVisible("css;#continue");
		actions.click(btnContinue);
		
		return new CheckOutStepTwoPage(this.driver);
	}
}
