package RahulShettyTut.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//*[contains(@class,'text-validated')])[3]")
	WebElement selcntry;
	
	@FindBy(xpath="//*[contains(@class,'ta-results')]//button[2]")
	WebElement lstcntry;
	
	@FindBy(xpath="//*[contains(@class,'action__submit')]")
	WebElement btnSubmt;
	
	By cntrylist= By.xpath("//*[contains(@class,'ta-results')]//button[2]");
	
	public void clickOnCntryField(String cntry)
	{
		Actions a = new Actions(driver);
		a.sendKeys(selcntry, cntry).build().perform();
		waitForElementToAppear(cntrylist);
		lstcntry.click();
	}
	
	public ConfirmationPage submit()
	{
		btnSubmt.click();
		return new ConfirmationPage(driver);
	}
}
