package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import utilities.Log;

public class TC_01_HomePageElementsValidation extends BaseTest {

    @Test
    public void verifyPageTitle(){
        HomePage home = new HomePage(driver);
        String expectedTitle = "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests";
        String actualTitle = home.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Test case 01 failed: Page title does not match!");
        Log.info("Test case 01 :");
        Log.info("Page title matched!");
        Log.info("Logo is displayed :"+home.isPageLogoDisplayed());
        Assert.assertTrue(home.isPageLogoDisplayed(), "Login is not displayed");
    }
}
