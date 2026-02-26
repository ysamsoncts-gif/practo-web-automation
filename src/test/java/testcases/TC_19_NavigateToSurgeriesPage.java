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
        Log.info("Sucessfully navigated to surgeries page");
    }

}
