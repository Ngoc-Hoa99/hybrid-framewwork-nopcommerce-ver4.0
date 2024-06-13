package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AddressesPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.OrdersPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointsPageObject;

public class PageGeneratorManager {
//	static để gọi trực tiếp từ tên hàm qua
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerPageObject getCustomerPage(WebDriver driver) {
		return new CustomerPageObject(driver);
	}
	
	public static AddressesPageObject getAddressesPage(WebDriver driver) {
		return new AddressesPageObject(driver);
	}
	
	public static OrdersPageObject getOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}
	
	public static RewardPointsPageObject getRewardPointsPage(WebDriver driver) {
		return new RewardPointsPageObject(driver);
	}
	
}
