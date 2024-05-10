package RahulShettyTut.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//*[@class='cart']//h3")
	List<WebElement> prdlist;
	
	@FindBy(xpath="(//*[@type='button'])[2]")
	WebElement btnCheckout;
	
	
	
	public List<WebElement> prdList()
	{
		return prdlist;
	}
	
	public Boolean checkPrdadded(String prodnme)
	{
		return prdlist.stream().anyMatch(prd -> prd.getText().equalsIgnoreCase(prodnme));

	}
	public CheckOutPage clickOnCheckt()
	{
		btnCheckout.click();
		CheckOutPage cp= new CheckOutPage(driver);
		return cp;
	}
}
