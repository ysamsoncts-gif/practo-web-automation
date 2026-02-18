package testcases;


import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_01_GetURL extends BaseTest {




    @Test
    public void verifyClick() {
        HomePage h = new HomePage(driver);
        h.NavigateToDiagnosticPage();
    }


}
