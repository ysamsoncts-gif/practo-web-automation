package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;

public class TC_14_InvalidForm extends BaseTest {

    @Test
    public void fillingInvalidData()
    {
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();

        cp.validatingData("Samidha","cts","12345","samidha@123",3,2);
        WebElement submitButton = driver.findElement(cp.submit);
        Assert.assertFalse(submitButton.isEnabled(), "Submit button should not be enabled.");
    }
}
