package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;
import pageobjects.HomePage;

@Test
public class TC_07_VerifyPage extends BaseTest {

    public void getPageTitle(){
        HomePage home = new HomePage(driver);
        home.NavigateToDiagnosticPage();


        DiagnosticPage diagnostic = new DiagnosticPage(driver);
       String actualTitle= diagnostic.GetPageTitle();
       String expectedTitle = "Blood Tests | Book Diagnostic Tests from Home at Best Prices | Practo";

       System.out.println("Expected Title : " + expectedTitle);
        System.out.println("Actual Title : " + actualTitle);

        Assert.assertEquals(actualTitle,expectedTitle,"Page title does not match the expected title!");

    }
}
