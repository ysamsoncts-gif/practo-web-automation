package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import pageobjects.MedicinePage;
import utilities.Log;

public class TC_17_NavigateToMedicinePage extends BaseTest {
    @Test
    public void navigateToMedicinePage(){
        Log.info("Starting test case: NavigateToMedicinePage");
        MedicinePage mp = new MedicinePage(driver);
        mp.navigateToLabTest();
        mp.navigateToMedicine();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Buy Medicines,Health Products Online | India's Most Reliable Online Medical Store | Practo";
        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Page title mismatch after navigating to Medicine page."
        );
        Log.info("Ending test case: NavigateToMedicinePage");
    }
}


