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
        Log.info("Test case 01 started:");
        Assert.assertEquals(actualTitle, expectedTitle, "Test case 01 failed: Page title does not match!");
        Log.info("Page title matched!");
        Assert.assertTrue(home.isPageLogoDisplayed(), "Login is not displayed");
        Log.info("Logo is displayed :"+home.isPageLogoDisplayed());
        Assert.assertEquals(home.numberOfCardLinkPresent(),21,"card links are not present as per the requirements");
        Log.info("Card links are present as per the requirements :"+home.numberOfCardLinkPresent());
        Assert.assertEquals(home.numberOfLinkTabPresent(),4,"Tab links are not as per the requirements");
        Log.info("Tab links are present as per the requirements :"+home.numberOfLinkTabPresent());
        Log.info("Test case 01 passed.");

    }
}
