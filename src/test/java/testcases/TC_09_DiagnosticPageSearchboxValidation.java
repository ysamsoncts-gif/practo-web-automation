package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;

import java.util.List;

@Test
public class TC_09_DiagnosticPageSearchboxValidation extends BaseTest {
    public void verifySearchBox() {
        DiagnosticPage diagnostic = new DiagnosticPage(driver);

        diagnostic.navigateToDiagnosticPage();

        diagnostic.searchBox("Pune");
        diagnostic.chooseCity("Pune");
        diagnostic.clickScroll();
//        diagnostic.storePackageNames();
        diagnostic.printPackageDetails();

//
//        List<WebElement> names = diagnostic.getPackageNames();
//        List<WebElement> ages = diagnostic.getPackageAgeGroups();
//        List<WebElement> prices = diagnostic.getPackagePrices();
//
//
//        Assert.assertFalse(names.isEmpty(), "No packages found!");
//        Assert.assertEquals(names.size(), ages.size(), "Name–Age mismatch!");
//        Assert.assertEquals(names.size(), prices.size(), "Name–Price mismatch!");
//
//
//        System.out.println("\n--- PACKAGES IN PUNE ---");
//        for (int i = 0; i <= names.size(); i++) {
//            String n = names.get(i).getText().trim();
//            String a = ages.get(i).getText().trim();
//            String p = prices.get(i).getText().trim();
//
//            System.out.println(n + " | " + a + " | " + p);
//
//            Assert.assertFalse(n.isBlank());
//            Assert.assertTrue(a.contains("For Age"));
//            Assert.assertTrue(p.contains("₹"));


        }
    }

