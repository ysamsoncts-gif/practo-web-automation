package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;
import utilities.Log;

@Test
public class TC_07_DiagnosticPageTitleValidation extends BaseTest {

    public void getPageTitle(){
        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        Log.info("Test case 07 started:");
        diagnostic.navigateToDiagnosticPage();
        String actualTitle= diagnostic.GetPageTitle();
        String expectedTitle = "Blood Tests | Book Diagnostic Tests from Home at Best Prices | Practo";

        System.out.println("Expected Title : " + expectedTitle);
        System.out.println("Actual Title : " + actualTitle);
        Log.info("Validating the actual and expected title.");
        Assert.assertEquals(actualTitle,expectedTitle,"Page title does not match the expected title!");
        Log.info("Test case 07 passed.");

    }
}
