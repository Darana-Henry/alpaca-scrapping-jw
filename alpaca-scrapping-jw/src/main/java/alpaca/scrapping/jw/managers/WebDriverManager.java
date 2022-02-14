package alpaca.scrapping.jw.managers;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
	private static WebDriver driver = null;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public void closeDriver() {
		driver.quit();
	}

	public WebDriver getDriver() {
		if (driver == null) {
			driver = createDriver();
		}
		return driver;

	}

	private WebDriver createDriver() {

		System.setProperty(CHROME_DRIVER_PROPERTY,
				System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver93");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		return driver;
	}
}
