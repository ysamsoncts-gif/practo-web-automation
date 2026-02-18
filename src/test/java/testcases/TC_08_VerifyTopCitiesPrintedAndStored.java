package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;
import pageobjects.HomePage;

import java.util.List;

public class TC_08_VerifyTopCitiesPrintedAndStored extends BaseTest {

    @Test
    public void verifyTopCitiesPrintedAndStored() {

        // 1) Navigate: Home -> Diagnostics
        HomePage home = new HomePage(driver);
        home.NavigateToDiagnosticPage();

        // 2) Initialize DiagnosticPage and wait for "Top Cities" to be visible
        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        diagnostic.waitForTopCities();

        // 3) Get all city names and store in a List
        List<String> topCities = diagnostic.getTopCities();

        // 4) Print cities in console (as per requirement)
        diagnostic.printTopCities(topCities);

        // ===== Assertions =====
        // A) List should not be null
        Assert.assertNotNull(topCities, "Top Cities list is null!");

        // B) List should have at least one item
        Assert.assertTrue(topCities.size() > 0, "Top Cities list is empty!");

        // C) None of the city names should be blank
        for (String city : topCities) {
            Assert.assertTrue(city != null && !city.trim().isEmpty(),
                    "Found a blank city name in Top Cities list!");
        }

    }
}
