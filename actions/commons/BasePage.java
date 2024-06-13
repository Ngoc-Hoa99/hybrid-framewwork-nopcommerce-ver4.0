package commons;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
//	Không cần khởi tạo màn vẫn truy cập vào đối tượng này được
	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageURL(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();

	}

	public void refreshCurentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {

		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowPageTitile(WebDriver driver, String expectedPageTitle) {
		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closedAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> windowAllIDs = driver.getWindowHandles();
		for (String id : windowAllIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);

	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		new Select(getWebElement(driver, xpathLocator)).selectByVisibleText(textItem);
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		return new Select(getWebElement(driver, xpathLocator)).getFirstSelectedOption().getText();
	}

	public boolean IsDropdownMultiple(WebDriver driver, String xpathLocator) {
		return new Select(getWebElement(driver, xpathLocator)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}

	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(WebDriver driver, String xpathLocator) {
		return Color.fromString(getElementCssValue(driver, xpathLocator, "background-color")).asHex();

	}

	public int getListElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	/**
	 * Apply for checkbox and Radio button
	 * 
	 * @param driver
	 * @param xpathLocator
	 */
	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		if (!getWebElement(driver, xpathLocator).isSelected()) {
			getWebElement(driver, xpathLocator).click();
		}
	}

	/**
	 * Only apply for checkbox
	 * 
	 * @param driver
	 * @param xpathLocator
	 */
	public void unCheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		if (getWebElement(driver, xpathLocator).isSelected()) {
			getWebElement(driver, xpathLocator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(xpathLocator)));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).doubleClick(getWebElement(driver, xpathLocator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).contextClick(getWebElement(driver, xpathLocator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLoacator, String targetLoacator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceLoacator), getWebElement(driver, targetLoacator))
				.perform();
	}

	public void senkeyBoardToElement(WebDriver driver, String xpathLocator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);

	}

	public String getDomainName(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, xpathLocator));
	}

	public void scrollToElementOnDown(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getWebElement(driver, xpathLocator));
	}

	public void setAttributeInDOM(WebDriver driver, String xpathLocator, String attributeName,
			String attributeValue) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathLocator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, xpathLocator));
	}

	public String getAttributeInDOM(WebDriver driver, String xpathLocator, String attributeName) {
		return (String) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, xpathLocator));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathLocator));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));

	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		 new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));

	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		System.out.println("Driver at wait for element clickable = " + driver.toString());
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));

	}
	
	private long longTimeout = 15;

}
