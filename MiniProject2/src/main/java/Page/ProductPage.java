package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ActionKeyword;
import Utilities.BasePage;

public class ProductPage extends BasePage{
	ActionKeyword actions;
	private WebElement btnAddToCart,btnCart;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		actions = new ActionKeyword(this.driver);
		// TODO Auto-generated constructor stub
	}
	
	public CartPage toCartPage() throws Exception {
		btnAddToCart = actions.waitElementVisible("css;.btn_inventory");
		actions.click(btnAddToCart);
	
		btnCart = actions.waitElementVisible("css;.shopping_cart_container");
		actions.click(btnCart);
		
		return new CartPage(this.driver);
	}
}
