package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;
import pageobjects.HomePage;

public class TC_04_HomePageLoginFormValidation extends BaseTest {

    @Test
    public void verifyForm(){
        HomePage home = new HomePage(driver);

        home.navigateToLoginPage();
        home.sendDataToInputBox("797776969","demoPassword");
        home.clickLoginButton();

        System.out.println("Test case 04 :");
        System.out.println("Login Form ");
        System.out.println("-------------");

        System.out.println("Invalid email/mobile input error message is displayed :"+home.validatePhoneEmailErrorMessage());
        System.out.println();
        Assert.assertEquals(home.validatePhoneEmailErrorMessage(),"Email/Mobile is not valid","Phone number/Email error message not displayed");

        home.sendDataToInputBoxB("","");
        home.clickLoginButton();

        System.out.println("Email/Mobile empty input box error message is displayed :"+home.validatePhoneEmailErrorMessage());
        System.out.println();
        Assert.assertEquals(home.validatePhoneEmailErrorMessage(),"Mobile Number / Email ID field cannot be empty","Phone number/Email input box empty error message not displayed");
        System.out.println("Password empty input box error message is displayed :"+home.validatePasswordErrorMessage());
        System.out.println();
        Assert.assertEquals(home.validatePasswordErrorMessage(),"Password field cannot be empty","Password input box empty error message not displayed");
    }
}
