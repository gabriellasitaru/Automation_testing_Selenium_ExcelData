package uk.co.automationtesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;

import pageObjects.ShopHomepage;
import pageObjects.ShopLoginPage;
import pageObjects.ShopYourAccount;


@Listeners(resources.Listeners.class)

public class ShopLoginTest extends Hooks {

	public ShopLoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	



@Test
public void addRemoveItem() throws IOException {

	ExtentManager.log("Starting ShopLoginTest...");

	// creating an object of the automationtesting.co.uk webpage
	Homepage home = new Homepage();
	home.getTestStoreLink().click();

	// creating an object of the test store homepage
	ShopHomepage shopHome = new ShopHomepage();
	shopHome.getloginBtn().click();

	FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") + 
			"\\src\\main\\java\\resources\\credentials.xlsx");
	
	XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
	XSSFSheet sheet = workbook.getSheetAt(0);
	
	
	
	Row row1 = sheet.getRow(1);
	Cell cellR1C0 = row1.getCell(0);
	Cell cellR1C1 = row1.getCell(1);
	
	String emailRow1 = cellR1C0.toString();
	String passwordRow1 = cellR1C1.toString();
	
	System.out.println(emailRow1);
	System.out.println(passwordRow1);
	
	ShopLoginPage shop = new ShopLoginPage();
	shop.getEmail().sendKeys(emailRow1);
	shop.getPassword().sendKeys(passwordRow1);
	shop.getSubmitBtn().click();
	
	
	ShopYourAccount yourAcc = new ShopYourAccount();
	
	try {
		yourAcc.getSignOutBtn().click();
		ExtentManager.pass("User HAS signed in");
	} catch (Exception e) {
		ExtentManager.fail("User could NOT sign in");
		Assert.fail();
	}

	
	
	
	Row row2 = sheet.getRow(2);
	Cell cellR2C0 = row2.getCell(0);
	Cell cellR2C1 = row2.getCell(1);
	
	String emailRow2 = cellR2C0.toString();
	String passwordRow2 = cellR2C1.toString();
	
	shop.getEmail().sendKeys(emailRow2);
	shop.getPassword().sendKeys(passwordRow2);
	shop.getSubmitBtn().click();
	
	try {
		yourAcc.getSignOutBtn().click();
		ExtentManager.pass("User HAS signed in");
	} catch (Exception e) {
		ExtentManager.fail("User could NOT sign in");
		Assert.fail();
	}
	
}

}