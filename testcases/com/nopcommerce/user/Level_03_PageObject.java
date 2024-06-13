package com.nopcommerce.user;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_PageObject {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		// Mở 1 URL nó ở trang nào thì khởi tạo trang đó lên
		// Từ 1 page này chuyển sang page kia --> thì khởi tạo page đó lên
		homePage = new HomePageObject(driver);
	}

	@Test
	public void User_01_Register_Empty_Data() {
		homePage.clickToRegisterLink();
		// Từ Honme page click registerlink --> Register page
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");

	}

	@Test
	public void User_02_Register_Invalid_Email() {
		registerPage.clickToNopCommerceLogo();
//		Từ register Page click logo nopCommerce thì hệ thống hiển thị Home Page
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();

//		Từ Honme page click registerlink --> Register page
		registerPage = new RegisterPageObject(driver);

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
		registerPage.clickToNopCommerceLogo();
//		Từ register Page click logo nopCommerce thì hệ thống hiển thị Home Page
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();

//		Từ Honme page click registerlink --> Register page
		registerPage = new RegisterPageObject(driver);

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
		registerPage.clickToNopCommerceLogo();
//		Từ register Page click logo nopCommerce thì hệ thống hiển thị Home Page
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();

//		Từ Honme page click registerlink --> Register page
		registerPage = new RegisterPageObject(driver);

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
		registerPage.clickToNopCommerceLogo();
//		Từ register Page click logo nopCommerce thì hệ thống hiển thị Home Page
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();

//		Từ Honme page click registerlink --> Register page
		registerPage = new RegisterPageObject(driver);

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
		// Từ register Page click logo nopCommerce thì hệ thống hiển thị Home Page
		homePage = new HomePageObject(driver);
		homePage.clickToLoginLink();

		// Từ Home Page click vào login link thì hệ thống sẽ mở Login Page
		loginPage = new LoginPageObject(driver);

		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox("12345678");
		loginPage.clickToLoginButton();
		

		// Từ Login Page --> login thành công thì hệ thống hiển thị Home Page

		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountLink();
		
		// Từ Home Page --> click vào My Account Link thì hệ thống hiển thị Customer
		// Account Page
		customerPage = new CustomerPageObject(driver);

		Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "Automation");
		Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Testing");
		Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), emailAddress);

	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "Join" + rand.nextInt(9999) + "@kenedy.us";
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
