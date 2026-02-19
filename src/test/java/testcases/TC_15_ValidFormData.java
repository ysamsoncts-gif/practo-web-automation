package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;

public class TC_15_ValidFormData extends BaseTest {

    @Test
    public void filingValidData()
    {
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();

        cp.validatingData("Samidha","cts","9898989898","cts@gmail.com",3,2);
        WebElement submitButton = driver.findElement(cp.submit);
        Assert.assertTrue(submitButton.isEnabled(), "Submit button should be enabled.");
    }
}
