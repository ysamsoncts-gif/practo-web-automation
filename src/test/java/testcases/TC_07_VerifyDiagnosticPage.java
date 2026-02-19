package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;

@Test
public class TC_07_VerifyDiagnosticPage extends BaseTest {

    public void getPageTitle(){
        DiagnosticPage diagnostic = new DiagnosticPage(driver);
        diagnostic.navigateToDiagnosticPage();

        String actualTitle= diagnostic.GetPageTitle();
        String expectedTitle = "Blood Tests | Book Diagnostic Tests from Home at Best Prices | Practo";

        System.out.println("Expected Title : " + expectedTitle);
        System.out.println("Actual Title : " + actualTitle);

        Assert.assertEquals(actualTitle,expectedTitle,"Page title does not match the expected title!");

    }
}
