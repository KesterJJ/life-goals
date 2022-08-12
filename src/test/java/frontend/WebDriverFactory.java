package frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	public static WebDriver getDriver() throws Exception {
		String webDriver = System.getProperty("browser", "chrome");

		switch (webDriver.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			return new ChromeDriver();
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			return new FirefoxDriver();
		default:
			throw new Exception(
					"[Fatal] No driver available: No browser property supplied and could not default to ChromeDriver");
		}
	}
}
