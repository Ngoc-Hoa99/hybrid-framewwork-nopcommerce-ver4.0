package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObjects.AddressesPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.OrdersPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointsPageObject;

public class Level_08_Switch_Page extends BaseTest {
	WebDriver driver;

	String osName = System.getProperty("os.name");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private AddressesPageObject addressPage;
	private OrdersPageObject oderPage;
	private RewardPointsPageObject rewwardPointPage;
	private String emailAddress = getEmailRandom();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
	}

	@Test
	public void User_01_Register_successfull() {
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
	public void User_02_Login_Success() {
	
		loginPage = homePage.clickToLoginLink();

		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox("12345678");
		
		homePage = loginPage.clickToLoginButton();
		customerPage = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "Automation");
		Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Testing");
		Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), emailAddress);

	}
	
	@Test
	public void User_03_Switch_Page() {
//		customerPage --> Address page
		addressPage = customerPage.OpenAddressPage();
		
//		customerPage --> Order page
		oderPage = customerPage.OpenOrdersPage();
		
//		customerPage --> Reward page
		rewwardPointPage = customerPage.OpenRewardPointsPage();
		
	}

	@AfterClass
	public void afterClass() {
		closedBrowser();
	}
}
