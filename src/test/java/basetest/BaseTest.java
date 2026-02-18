package basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.DriverFactory;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browserFromConfig = ConfigReader.get("browser", "chrome");

        boolean headless = ConfigReader.getBoolean("headless", false);
        String baseUrl = ConfigReader.get("baseUrl");

        driver = DriverFactory.getDriver(browserFromConfig, headless);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
