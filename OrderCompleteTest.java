package uk.co.automationtesting;

import java.io.IOException;


import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;
@Listeners(resources.Listeners.class)

public class OrderCompleteTest extends Hooks {

	public OrderCompleteTest() throws IOException {
		super();
		
	}
	
	
	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		ExtentManager.log("Starting OrderCompleteTest...");
		
		Homepage home = new Homepage();
		home.getTestStoreLink().click();
		ExtentManager.pass("Have succesfully reached store homepage");
		
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();
		ExtentManager.pass("Have succesfully clicked on product");
		
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Have succesfully reached shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have succesfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have succesfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have succesfully added item to cart");
		
		Thread.sleep(5000);
		
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();
		
		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have succesfully reached the shopping cart page");
		cart.getHavePromo().click();
		ExtentManager.pass("Have succesfully selected the promo button");
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		
		Thread.sleep(3000);
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have succesfully selected the checkout button");
		
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("Jason");
		pInfo.getLastnameField().sendKeys("Doe");
		pInfo.getEmailField().sendKeys("jasondoe@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Have succesfully entered customer details");
		
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		Thread.sleep(3000);
		orderDelivery.getContinueBtn().click();
		ExtentManager.pass("Have succesfully entered the delivery info");
		
		Thread.sleep(3000);
		
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();
		Thread.sleep(3000);
		ExtentManager.pass("Have succesfully selected the shipping method");
		
		
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have succesfully placed the order");
		
		
		
	}

}

