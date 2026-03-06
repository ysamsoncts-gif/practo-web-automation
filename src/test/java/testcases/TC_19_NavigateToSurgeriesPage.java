package testcases;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageobjects.SurgeriesPage;

import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.Log;

public class TC_19_NavigateToSurgeriesPage extends BaseTest {

    @Test
    public void verifyNavigation() {
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Log.info("Starting test case: verifyNavigation");
        Assert.assertTrue(
                driver.getCurrentUrl().toLowerCase().contains("care"),
                "Navigation to Surgeries Failed."
        );
        sp.isLogoDisplayed();
        Assert.assertTrue(sp.getHeadingText().contains("Surgeons"),"page heading dont contain Surgeons words");
        Assert.assertTrue(sp.isLogoDisplayed(),"Logo missing in Surgeries page");
        System.out.println(sp.getLogoAttribute("alt"));
        Assert.assertEquals(sp.getLogoAttribute("alt"),"PCS Logo","Incorrect Logo loaded");
        Assert.assertTrue(driver.getTitle().contains("Surgeries"),"Surgery page Title not matching or not available");
        Log.info("Sucessfully navigated to surgeries page");
    }
}
