package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;
import pageobjects.HomePage;
import utilities.Log;

import java.util.List;

public class TC_08_DiagnosticPageTopCitiesValidation extends BaseTest {

    @Test
    public void verifyTopCitiesPrintedAndStored() {

        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        Log.info("Test case 08 started:");
        diagnostic.navigateToDiagnosticPage();
        diagnostic.waitForTopCities();
        List<String> topCities = diagnostic.getTopCities();
        diagnostic.saveCityListToExcelFromList();
        Log.info("List of top cities saved in excel file");
        Assert.assertNotNull(topCities, "Top Cities list is null!");
        Assert.assertTrue(topCities.size() > 0, "Top Cities list is empty!");
        for (String city : topCities) {
            Assert.assertTrue(city != null && !city.trim().isEmpty(),
                    "Found a blank city name in Top Cities list!");
        }
        Log.info("Test case 08 passed.");

    }
}
