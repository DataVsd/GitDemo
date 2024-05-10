package RahulShettyTut.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class*='mb-3']")
	List<WebElement> products;

	By prdlist = By.cssSelector("[class*='mb-3']");
    By addTocrtBtn=By.xpath("//button[@class='btn w-10 rounded']");
    By toastMsg=By.id("toast-container");
    
	public List<WebElement> getProductList() {
		waitForElementToAppear(prdlist);
		List<WebElement> products = driver.findElements(prdlist);
		return products;
	}

	public WebElement getProductByName(String prodnme) {
		return getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodnme)).findFirst()
				.orElse(null);
	}

	public void addProdToCart(WebElement prod) {
		prod.findElement(addTocrtBtn).click();
		waitForElementToAppear(prdlist);
		waitForElementToDisappear(toastMsg);
	}
}
