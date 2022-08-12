package frontend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	
	WebDriverManager.chromedriver().setup();
	
	ChromeDriver cDriver = new ChromeDriver();
	
    private WebDriver driver2;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        cDriver = new ChromeDriver();
    }
    
    @Before
    public void setup2() {
        driver2 = WebDriverFactory.getDriver();
    }

    @Test
    public void someTest() {
        // some test stuff
    }

    @After
    public void tearDown() {
        cDriver.quit();
    }
    
    @After
    public void tearDown2() {
        driver2.quit();
    }
}
