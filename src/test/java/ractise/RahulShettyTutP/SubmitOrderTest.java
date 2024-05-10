package ractise.RahulShettyTutP;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.AbstractComponents.AbstractComponents;
import rahulshetty.testcomponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyTut.pageobjects.CartPage;
import RahulShettyTut.pageobjects.LandingPage;
import RahulShettyTut.pageobjects.OrderPage;
import RahulShettyTut.pageobjects.CheckOutPage;
import RahulShettyTut.pageobjects.ConfirmationPage;
import RahulShettyTut.pageobjects.ProductCataloguePage;

public class SubmitOrderTest extends BaseTest {
	String prodnme = "ZARA COAT 3";
	@Test(dataProvider= "getdata", groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException{
		
		String cnfrmmsg = "THANKYOU FOR THE ORDER.";

		ProductCataloguePage prdcatlpge=lp.loginApp(input.get("email"), input.get("pwd"));

		List<WebElement> products = prdcatlpge.getProductList();
		WebElement prod = prdcatlpge.getProductByName(input.get("prodnme"));
		prdcatlpge.addProdToCart(prod);
		CartPage cp=prdcatlpge.clickOnCartHeader();
		
		//cp.clickOnCart();
		List<WebElement> prdlist = cp.prdList();
		Boolean flag = cp.checkPrdadded(input.get("prodnme"));
		Assert.assertTrue(flag);
		CheckOutPage chkpge=cp.clickOnCheckt();
		
		chkpge.clickOnCntryField("India");
		ConfirmationPage cnfmpge=chkpge.submit();
		String actualmsg = cnfmpge.getCnfrmMsg();
		Assert.assertEquals(actualmsg, cnfrmmsg);
		System.out.println("Confirmation message is received");
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderTest() {
		ProductCataloguePage prdcatlpge=lp.loginApp("anshika@gmail.com", "IamQueen@000");
		OrderPage op=prdcatlpge.clickOnOrderHeader();
		Assert.assertTrue(op.verifyOrderDisplay(prodnme));
	}
	@DataProvider
	public Object[][] getdata() throws IOException

	{
		/*
		 * HashMap<String,String> map= new HashMap<String,String>(); map.put("email",
		 * "anshika@gmail.com"); map.put("pwd", "IamQueen@000"); map.put("prodnme",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1= new HashMap<String,String>(); map1.put("email",
		 * "shetty@gmail.com"); map1.put("pwd", "IamKing@000"); map1.put("prodnme",
		 * "ZARA COAT 3");
		 */
		List<HashMap<String, String>> data=getJsonDataToMap();
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
