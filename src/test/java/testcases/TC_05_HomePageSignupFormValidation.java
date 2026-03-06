package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;
import pageobjects.HomePage;
import utilities.Log;

public class TC_05_HomePageSignupFormValidation extends BaseTest {

    @Test
    public void verifySignupForm() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.navigateToLoginPage();
        Thread.sleep(2000);
        home.navigateToSignupPage();
        home.clickSendOtpButton();
        Log.info("Test case 05 started:");
        Log.info("validating signup form for home page");
        Log.info("Name field error message is displayed :"+home.validateNameErrorMessage());
        Assert.assertEquals(home.validateNameErrorMessage(),"Full Name field cannot be empty","Name field error message not displayed");
        Log.info("Phone number field error message is displayed :"+home.validatePhoneErrorMessage());
        Assert.assertEquals(home.validatePhoneErrorMessage(),"Mobile Number field cannot be empty","Mobile field error message not displayed");
        Log.info("Password field error message is displayed :"+home.validatePasswordMessage());
        Assert.assertEquals(home.validatePasswordMessage(),"Password field cannot be empty","Password field error message not displayed");
        Log.info("Test case 05 passed.");
    }
}
