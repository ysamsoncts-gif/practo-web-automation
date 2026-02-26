package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.DiagnosticPage;

import java.math.BigDecimal;

public class TC_10_DiagnosticPageAddToCart_FormValidation extends BaseTest {
    @Test
    public void verifyAddtoCart_FormValidation() throws InterruptedException {
        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        diagnostic.navigateToDiagnosticPage();
        diagnostic.searchBox("Pune");
        diagnostic.chooseCity("Pune");
        double actualTotal = Double.parseDouble(String.valueOf(diagnostic.addTopBookedTestsAndGetTotal()));
        diagnostic.proceedToCheckout();
        double expectedTotal = diagnostic.getTotalPriceFromCart();
        System.out.println(expectedTotal);

        Assert.assertEquals(actualTotal, expectedTotal);

    }
}
