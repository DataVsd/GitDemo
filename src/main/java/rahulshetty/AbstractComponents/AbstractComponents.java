package rahulshetty.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyTut.pageobjects.CartPage;
import RahulShettyTut.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver2)
	{
		this.driver=driver2;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//*[@class='btn btn-custom'])[3]")
	WebElement btncrt;
	
	@FindBy(xpath="(//*[@class='btn btn-custom'])[2]")
	WebElement btnorders;
	
	public CartPage clickOnCartHeader() {
		btncrt.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	public OrderPage clickOnOrderHeader() {
		btnorders.click();
		OrderPage op= new OrderPage(driver);
		return op;
		
	}
	public void waitForElementToAppear(By findBy)
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement findBy)
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(By findBy)
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
	}
	
	
}
