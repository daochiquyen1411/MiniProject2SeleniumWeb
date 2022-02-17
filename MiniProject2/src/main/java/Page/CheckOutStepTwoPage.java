package Page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class CheckOutStepTwoPage extends BasePage{
	ActionKeyword actions;
	private List<WebElement> valueLabelList;
	private WebElement btnCancel, btnFinish;
	
	public CheckOutStepTwoPage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isPaymentAndShippingInfoDisplay() throws Exception {
		valueLabelList = actions.FindElements("css;.summary_value_label");
		for (WebElement e : valueLabelList) {
			if(!e.isDisplayed()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isCancelAndFinishBtnEnabledAndDisplayed() throws Exception {
		btnCancel = actions.waitElementVisible("css;.cart_cancel_link");
		btnFinish  = actions.waitElementVisible("css;.cart_button");
		
		if(btnCancel.isEnabled() && btnCancel.isDisplayed() && btnFinish.isEnabled() && btnFinish.isDisplayed()) {
			return true;
		}
		return false;		
	}
	
}
