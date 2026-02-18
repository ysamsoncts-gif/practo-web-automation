package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_01_VerifyHomePageTitle extends BaseTest {

    @Test
    public void verifyPageTitle(){
        HomePage home = new HomePage(driver);
        String expectedTitle = "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests";
        String actualTitle = home.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Test case 01 failed: Page title does not match!");
        System.out.println("Test case 01 passed: Page title matched!");
    }
}
