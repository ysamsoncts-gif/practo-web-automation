package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;
import pageobjects.HomePage;
import utilities.Log;

public class TC_04_HomePageLoginFormValidation extends BaseTest {

    @Test
    public void verifyForm(){
        HomePage home = new HomePage(driver);

        home.navigateToLoginPage();
        home.sendDataToInputBox("797776969","demoPassword");
        home.clickLoginButton();

        Log.info("Test case 04 started:");
        Log.info("Validating login form on home page");
        Log.info("Invalid email/mobile input error message is displayed :"+home.validatePhoneEmailErrorMessage());
        Assert.assertEquals(home.validatePhoneEmailErrorMessage(),"Email/Mobile is not valid","Phone number/Email error message not displayed");
        home.sendDataToInputBoxB("","");
        home.clickLoginButton();
        Log.info("Email/Mobile empty input box error message is displayed :"+home.validatePhoneEmailErrorMessage());
        Assert.assertEquals(home.validatePhoneEmailErrorMessage(),"Mobile Number / Email ID field cannot be empty","Phone number/Email input box empty error message not displayed");
        Log.info("Password empty input box error message is displayed :"+home.validatePasswordErrorMessage());
        Assert.assertEquals(home.validatePasswordErrorMessage(),"Password field cannot be empty","Password input box empty error message not displayed");
        Log.info("Test case 04 passed.");
    }
}
