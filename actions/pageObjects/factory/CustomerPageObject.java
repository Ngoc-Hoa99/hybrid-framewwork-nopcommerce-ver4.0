package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class CustomerPageObject extends BasePageFactory{
	WebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public String getFirstNameTextboxAttributeValue() {
		waitForElementVisible(driver, firstNameTextbox);
		return getElementAttribute (driver, firstNameTextbox, "value");
	}
	public String getLastNameTextboxAttributeValue() {
		waitForElementVisible(driver, lastNameTextbox);
		return getElementAttribute (driver, lastNameTextbox, "value");
	}
	public String getEmailAddressTextboxAttributeValue() {
		waitForElementVisible(driver, emailTextbox);
		return getElementAttribute (driver, emailTextbox, "value");
	}
}
