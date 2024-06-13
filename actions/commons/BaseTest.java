package commons;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest {
	private WebDriver driver;
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
//		if (browser == BrowserList.CHROME) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//		}else if (browser == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		}else if(browser == BrowserList.EDGE) {
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
//		}else {
//			throw new RuntimeException("Browser name is not valid.");
//		}

//		Dùng switch clearn hơn
		switch (browser) {
		case CHROME:
//			WebDriverManager 5.x: Tải về driver + setting biến môi trường và khởi tạo browser lên
//			driver = WebDriverManager.chromedriver().create();
			
//			Chạy bằng selenium manager
			driver = new ChromeDriver();
			break;
			
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
			
		case EDGE:
			driver = new EdgeDriver();
			break;
			
		default:
			throw new RuntimeException("Browser name is not valid.");
		}
		
		driver.manage().window().maximize();
//		driver.manage().window().setPosition(new Point(0,0));
//		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://demo.nopcommerce.com/");
		return driver;
		
	}

	protected String getEmailRandom() {
		Random rand = new Random();
		return "Join" + rand.nextInt(9999) + "@kenedy.us";
	}
	
	protected void closedBrowser() {
		if (driver ==  null) {
			System.out.println("Browser is closed");
		} else {
			driver.quit();
		}
	}
}
