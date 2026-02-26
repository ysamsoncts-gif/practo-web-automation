package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;
import pageobjects.HomePage;

public class TC_05_HomePageSignupFormValidation extends BaseTest {

    @Test
    public void verifySignupForm() throws InterruptedException {
        HomePage home = new HomePage(driver);

        home.navigateToLoginPage();
        Thread.sleep(2000);
        home.navigateToSignupPage();
        home.clickSendOtpButton();

        System.out.println("Test case 05 :");
        System.out.println("Signup Form ");
        System.out.println("-------------");

        System.out.println("Name field error message is displayed :"+home.validateNameErrorMessage());
        System.out.println();
        Assert.assertEquals(home.validateNameErrorMessage(),"Full Name field cannot be empty","Name field error message not displayed");

        System.out.println("Phone number field error message is displayed :"+home.validatePhoneErrorMessage());
        System.out.println();
        Assert.assertEquals(home.validatePhoneErrorMessage(),"Mobile Number field cannot be empty","Mobile field error message not displayed");

        System.out.println("Password field error message is displayed :"+home.validatePasswordMessage());
        System.out.println();
        Assert.assertEquals(home.validatePasswordMessage(),"Password field cannot be empty","Password field error message not displayed");
    }
}
