package commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	protected void openPageURL(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();

	}

	protected void refreshCurentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected void switchToWindowByID(WebDriver driver, String windowID) {

		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowPageTitile(WebDriver driver, String expectedPageTitle) {
		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	protected void closedAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	protected Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	protected void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	
	protected void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	protected void sendKeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	protected String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOf(element));

	}
	
	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		System.out.println("Driver at wait for element clickable = " + driver.toString());
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));

	}
	
	private long longTimeout = 15;
	
}
