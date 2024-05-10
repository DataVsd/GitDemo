package RahulShettyTut.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userId;

	@FindBy(id = "userPassword")
	WebElement pswrd;

	@FindBy(id = "login")
	WebElement btnLogin;

	@FindBy(css = "[class*='toast-error']")
	WebElement loginerror;

	public ProductCataloguePage loginApp(String uname, String pwd) {
		userId.sendKeys(uname);
		pswrd.sendKeys(pwd);
		btnLogin.click();
		ProductCataloguePage prdcatlpge = new ProductCataloguePage(driver);
		return prdcatlpge;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}

	public String getErrorMessage() {
		waitForElementToAppear(loginerror);
		return loginerror.getText();
	}
}
