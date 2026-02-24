package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;

public class TC_16_ListAllServices extends BaseTest {
    @Test
    public void listAllServices()
    {
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();
        cp.navigateToOurServices();
        int sizeOfServiceList = cp.getSizeserviceItems();
        Assert.assertTrue(
                sizeOfServiceList > 0,
                "Services are not available"
        );
        cp.listOfAllServices();

    }
}
