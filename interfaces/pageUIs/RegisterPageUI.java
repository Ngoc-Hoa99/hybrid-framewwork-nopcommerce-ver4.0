package pageUIs;

public class RegisterPageUI {
	public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	
	public static final String FIRST_NAME_ERROR_MESSAGE = "//label[text()='First name:']//parent::div//span[@class='field-validation-error']";
	public static final String LAST_NAME_ERROR_MESSAGE = "//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE = "//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "//label[text()='Password:']//parent::div//span[@class='field-validation-error']";
	public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "//span[@id='ConfirmPassword-error']";
	public static final String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
	public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
	public static final String EXISTING_EMAIL_ERROR_MESSAGE = "//div[contains(@class,'message-error')]//li";
	public static final String NOP_COMMER_LOGO = "//div[@class='header-logo']//img";
	
}
