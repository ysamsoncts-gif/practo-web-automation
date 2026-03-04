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
        diagnostic.printPackageDetails();




        }
    }

