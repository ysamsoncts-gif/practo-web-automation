package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.DiagnosticPage;
import utilities.Log;

import java.math.BigDecimal;

public class TC_09_DiagnosticPageAddToCart extends BaseTest {
    @Test
    public void verifyAddtoCart_FormValidation() throws InterruptedException {
        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        Log.info("Test case 10 started:");
        diagnostic.navigateToDiagnosticPage();
        diagnostic.searchBox("Pune");
        diagnostic.chooseCity("Pune");
        double actualTotal = Double.parseDouble(String.valueOf(diagnostic.addTopBookedTestsAndGetTotal()));
        diagnostic.proceedToCheckout();
        double expectedTotal = diagnostic.getTotalPriceFromCart();
        System.out.println(expectedTotal);
        Log.info("Validating add to cart functionality");
        Assert.assertEquals(actualTotal, expectedTotal);
        Log.info("Test case 10 passed.");

    }
}
