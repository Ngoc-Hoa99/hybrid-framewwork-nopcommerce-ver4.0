package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage{
	WebDriver driver;
	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getFirstNameTextboxAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getEmailAddressTextboxAttributeValue() {
		waitForElementVisible(driver, CustomerPageUI.EMAIL_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerPageUI.EMAIL_NAME_TEXTBOX, "value");
	}

}
