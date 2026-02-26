package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import utilities.Log;

public class TC_16_ListAllServices extends BaseTest {
    @Test
    public void listAllServices()
    {
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();
        Log.info("Starting test case: ListAllServices");
        cp.navigateToOurServices();
        int sizeOfServiceList = cp.getSizeserviceItems();
        Assert.assertTrue(
                sizeOfServiceList > 0,
                "Services are not available"
        );
        cp.saveServicesToExcel();
        Log.info("Data stored into the Excel");
        Log.info("Ending test case: ListAllServices");
    }
}

