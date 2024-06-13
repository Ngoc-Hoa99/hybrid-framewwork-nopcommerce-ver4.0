package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory{
	WebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//input[@class='email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@class='password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;
	
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver,emailTextbox, emailAddress);
		
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver,passwordTextbox, password);
	}
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}
}
