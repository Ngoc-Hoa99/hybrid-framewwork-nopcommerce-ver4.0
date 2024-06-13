package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory{
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath = "//label[text()='First name:']//parent::div//span[@class='field-validation-error']")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath = "//label[text()='Password:']//parent::div//span[@class='field-validation-error']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;
	
	@FindBy(xpath = "//div[@class='header-logo']//img")
	private WebElement nopCommerceLogo;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
		
	}
	public String getFirstNameErrorMessageText() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver,firstNameErrorMessage );
	}
	public String getLastNameErrorMessageText() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver,lastNameErrorMessage );
	}
	public String getEmailErrorMessageText() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver,emailErrorMessage );
	}
	public String getConfirmPasswordErrorMessageText() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver,confirmPasswordErrorMessage );
	}
	public void clickToNopCommerceLogo() {
		waitForElementClickable(driver, nopCommerceLogo);
		clickToElement(driver, nopCommerceLogo);
		
	}
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement (driver, firstNameTextbox, firstName);
		
	}
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement (driver, lastNameTextbox, lastName);
		
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement (driver, emailTextbox, email);
		
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement (driver, passwordTextbox, password);
		
	}
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendKeyToElement (driver, confirmPasswordTextbox, confirmPassword);
		
	}
	public String getPasswordErrorMessageText() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver,passwordErrorMessage);
	}
	public String getRegisterSuccessMesageText() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver,registerSuccessMessage);
	}
	public void clickToLogout() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
		
	}
}
