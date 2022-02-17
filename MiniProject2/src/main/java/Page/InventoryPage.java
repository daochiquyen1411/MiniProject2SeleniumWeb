package Page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class InventoryPage extends BasePage{
	ActionKeyword actions;
	private WebElement lblInventorypage,lblProduct;
	private List<WebElement> listProduct;
	
	public InventoryPage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isLoginSuccessful() throws Exception {
		lblInventorypage = actions.waitElementVisible("css;.title");
		if(lblInventorypage.isDisplayed()) {			
			return true;
		}
		return false;
	}
	//.inventory_item_label
	public ProductPage toProductPage() throws Exception {
//		lblProduct = actions.waitElementVisible("xpath;//*[contains(text(), 'Sauce Labs Backpack')]");
//		actions.click(lblProduct);
		
		listProduct = actions.FindElements("css;.inventory_item_img");
		lblProduct = listProduct.get(0);
		actions.click(lblProduct);
		
		return new ProductPage(this.driver);
	}
}
