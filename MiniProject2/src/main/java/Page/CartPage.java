package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class CartPage extends BasePage{
	ActionKeyword actions;
	private WebElement btnCheckOut;
	public CartPage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
		// TODO Auto-generated constructor stub
	}

	public CheckOutStepOnePage toCheckOutStepOnePage() throws Exception {
		btnCheckOut = actions.waitElementVisible("css;.checkout_button");
		actions.click(btnCheckOut);
		
		return new CheckOutStepOnePage(this.driver);
	}
}
