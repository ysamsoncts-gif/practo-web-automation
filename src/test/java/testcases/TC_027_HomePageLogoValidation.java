package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_027_HomePageLogoValidation extends BaseTest {

    @Test
    public void verifyLogo(){
        HomePage home = new HomePage(driver);
        System.out.println("Logo is displayed :"+home.isPageLogoDisplayed());
        Assert.assertTrue(home.isPageLogoDisplayed(), "Login is not displayed");
    }
}
