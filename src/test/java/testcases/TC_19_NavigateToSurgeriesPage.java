package testcases;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageobjects.SurgeriesPage;

import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_19_NavigateToSurgeriesPage extends BaseTest {

    @Test
    public void verifyNavigation() {
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();

        Assert.assertTrue(
                driver.getCurrentUrl().toLowerCase().contains("care"),
                "Navigation to Surgeries Failed."
        );

    }

}
