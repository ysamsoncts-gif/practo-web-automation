package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_01_HomePageElementsValidation extends BaseTest {

    @Test
    public void verifyPageTitle(){
        HomePage home = new HomePage(driver);
        String expectedTitle = "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests";
        String actualTitle = home.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Test case 01 failed: Page title does not match!");
        System.out.println("Test case 01 :");
        System.out.println("Page title matched!");
        System.out.println("Logo is displayed :"+home.isPageLogoDisplayed());
        Assert.assertTrue(home.isPageLogoDisplayed(), "Login is not displayed");
    }
}
