package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_07_Page_Manager_2 extends BaseTest {
	WebDriver driver;

	String osName = System.getProperty("os.name");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_Empty_Data() {	
		registerPage = homePage.clickToRegisterLink();
	
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");

	}

	@Test
	public void User_02_Register_Invalid_Email() {
		homePage = registerPage.clickToNopCommerceLogo();

		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("Automation");
		registerPage.enterToLastNameTextbox("Testing");
		registerPage.enterToEmailTextbox("1111");
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345678");

		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Please enter a valid email address.");

	}

	@Test
	public void User_03_Register_Invalid_Password() {
		homePage = registerPage.clickToNopCommerceLogo();
		
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("Automation");
		registerPage.enterToLastNameTextbox("Testing");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123");
		registerPage.enterToConfirmPasswordTextbox("123");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getPasswordErrorMessageText(),
				"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

	}

	@Test
	public void User_04_Register_Invalid_Password_Confirm() {
		homePage = registerPage.clickToNopCommerceLogo();

		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("Automation");
		registerPage.enterToLastNameTextbox("Testing");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345");

		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),
				"The password and confirmation password do not match.");

	}

	@Test
	public void User_05_Register_successfull() {
		homePage = registerPage.clickToNopCommerceLogo();

		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("Automation");
		registerPage.enterToLastNameTextbox("Testing");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345678");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMesageText(), "Your registration completed");
		registerPage.clickToLogout();
		
	}

	@Test
	public void User_06_Login_Success() {
	
		loginPage = homePage.clickToLoginLink();

		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox("12345678");
		
		homePage = loginPage.clickToLoginButton();
		customerPage = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "Automation");
		Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Testing");
		Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), emailAddress);

	}

	@AfterClass
	public void afterClass() {
		closedBrowser();
	}
}
