package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_13_navToWellnessPage extends BaseTest {

    @Test
    public void navToHealthAndWellnessPage() {
        HomePage hm = new HomePage(driver);
        hm.navigateToCorporateTab();
        hm.navigateToHealthWellnessPage();

        String actualTitle = driver.getTitle();
        String expectedTitle = "Employee Health | Corporate Health & Wellness Plans | Practo";
        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Page title mismatch after navigating to Health & Wellness page."
        );



    }
}
