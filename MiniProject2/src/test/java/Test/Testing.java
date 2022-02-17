package Test;

import org.testng.annotations.Test;

import Page.CartPage;
import Page.CheckOutStepOnePage;
import Page.CheckOutStepTwoPage;
import Page.InventoryPage;
import Page.LoginPage;
import Page.ProductPage;
import Utilities.DataDriven;

import org.testng.Assert;

public class Testing extends BaseTest{
	
	@Test(dataProvider = "jsonLoginUser", dataProviderClass = DataDriven.class)
	public void Testing(String username, String password) throws Exception {
		LoginPage loginPage = new LoginPage(this.driver);
		InventoryPage inventoryPage = loginPage.loginToInventoryPage(username, password);
		Assert.assertTrue(inventoryPage.isLoginSuccessful(),"Login fail");	
		ProductPage productPage = inventoryPage.toProductPage();
		CartPage cartPage = productPage.toCartPage();
		CheckOutStepOnePage checkOutStepOnePage = cartPage.toCheckOutStepOnePage();
		CheckOutStepTwoPage checkOutStepTwoPage = checkOutStepOnePage.toCheckOutStepTwoPage();
		Assert.assertTrue(checkOutStepTwoPage.isPaymentAndShippingInfoDisplay(),"Loading payment and shipping info fail");
		Assert.assertTrue(checkOutStepTwoPage.isCancelAndFinishBtnEnabledAndDisplayed(),"Some error occur with cancel or finish button");		
	}
}
