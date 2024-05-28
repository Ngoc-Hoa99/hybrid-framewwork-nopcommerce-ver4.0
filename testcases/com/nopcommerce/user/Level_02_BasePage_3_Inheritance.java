package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_BasePage_3_Inheritance extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//button[@id='register-button']");
		
		sleepInSecond(3);
		Assert.assertEquals(getElementText(driver, "//span[@class='field-validation-error']//span[@id='FirstName-error']"),"First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"),"Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", "1111");
		sendKeyToElement(driver, "//input[@id='Password']", "12345678");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345678");
		
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Please enter a valid email address.");
	}

	@Test
	public void Register_03_Invalid_Password() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "12345");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");

		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@class='field-validation-error']"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
	}
	

	@Test
	public void Register_04_Invalid_Password_Confirm() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "12345678");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),"The password and confirmation password do not match.");
	}
	
	@Test
	public void Register_05_successfull (){
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", getEmailRandom());
		sendKeyToElement(driver, "//input[@id='Password']", "12345678");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345678");
		
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
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
