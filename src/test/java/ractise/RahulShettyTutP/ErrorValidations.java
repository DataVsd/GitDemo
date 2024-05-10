package ractise.RahulShettyTutP;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyTut.pageobjects.CartPage;
import RahulShettyTut.pageobjects.CheckOutPage;
import RahulShettyTut.pageobjects.ProductCataloguePage;
import rahulshetty.testcomponents.BaseTest;

public class ErrorValidations extends BaseTest {
	@Test(groups= {"ErrorHandling"})
	public void loginError() throws IOException {
		String prodnme = "ZARA COAT 3";
		String cnfrmmsg = "THANKYOU FOR THE ORDER.";
		ProductCataloguePage prdcatlpge = lp.loginApp("anshika@gmail.com", "Iamking@0");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		System.out.println("Error is caught");
	}
	
	@Test
	public void productErrorValidation() {
		ProductCataloguePage prdcatlpge = lp.loginApp("anshika@gmail.com", "IamQueen@000");

		List<WebElement> products = prdcatlpge.getProductList();
		WebElement prod = prdcatlpge.getProductByName("ZARA COAT 3");
		prdcatlpge.addProdToCart(prod);
		CartPage cp = prdcatlpge.clickOnCartHeader();

		// cp.clickOnCart();
		List<WebElement> prdlist = cp.prdList();
		Boolean flag = cp.checkPrdadded("ZARA COAT 33");
		Assert.assertFalse(flag);
		System.out.println("The product added to cart is not available in the product list");
		CheckOutPage chkpge = cp.clickOnCheckt();
	}
}