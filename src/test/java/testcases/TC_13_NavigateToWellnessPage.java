package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import pageobjects.HomePage;
import utilities.Log;

public class TC_13_NavigateToWellnessPage extends BaseTest {

    @Test
    public void navToHealthAndWellnessPage() {
        Log.info("Starting test case: NavigateToWellnessPage");
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Employee Health | Corporate Health & Wellness Plans | Practo";
        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Page title mismatch after navigating to Health & Wellness page."
        );
        Log.info("Ending test case: NavigateToWellnessPage");
    }
}
