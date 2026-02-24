package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;

public class    TC_05_FindDoctorsPageFormValidation extends BaseTest {

    @Test
    public void verifyForm(){
        FindDoctorsPage doctors = new FindDoctorsPage(driver);

        doctors.navigateToLoginPage();
        doctors.sendDataToInputBox("797776969","demoPassword");
        doctors.clickLoginButton();

        System.out.println("Test case 05 :");
        System.out.println("Login Form ");
        System.out.println("-------------");

        System.out.println("Invalid email/mobile input error message is displayed :"+doctors.validatePhoneEmailErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validatePhoneEmailErrorMessage(),"Email/Mobile is not valid","Phone number/Email error message not displayed");

        doctors.sendDataToInputBox2("","");
        doctors.clickLoginButton();

        System.out.println("Email/Mobile empty input box error message is displayed :"+doctors.validatePhoneEmailErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validatePhoneEmailErrorMessage(),"Mobile Number / Email ID field cannot be empty","Phone number/Email input box empty error message not displayed");
        System.out.println("Password empty input box error message is displayed :"+doctors.validatePasswordErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validatePasswordErrorMessage(),"Password field cannot be empty","Password input box empty error message not displayed");

        doctors.navigateToSignupPage();
        doctors.clickSendOtpButton();

        System.out.println("Signup Form ");
        System.out.println("-------------");

        System.out.println("Name field error message is displayed :"+doctors.validateNameErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validateNameErrorMessage(),"Full Name field cannot be empty","Name field error message not displayed");

        System.out.println("Phone number field error message is displayed :"+doctors.validatePhoneErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validatePhoneErrorMessage(),"Mobile Number field cannot be empty","Mobile field error message not displayed");

        System.out.println("Password field error message is displayed :"+doctors.validatePasswordErrorMessage());
        System.out.println();
        Assert.assertEquals(doctors.validatePasswordErrorMessage(),"Password field cannot be empty","Password field error message not displayed");
    }
}
