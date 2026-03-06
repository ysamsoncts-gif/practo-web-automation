package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DiagnosticPage;
import utilities.Log;

public class TC_10_DiagnosticPageFormValidation extends BaseTest {
    @Test
    public void formValidationNegativeCase(){
        DiagnosticPage  diagnostic = new DiagnosticPage(driver);
        Log.info("Test case 11 started:");
        diagnostic.navigateToDiagnosticPage();
        diagnostic.searchBox("Pune");
        diagnostic.chooseCity("Pune");
        diagnostic.addTopBookedTestsAndGetTotal();
        diagnostic.proceedToCheckout();
        diagnostic.sendName(" ");
        diagnostic.sendDobAge(" ");
        diagnostic.selectYearDropDown();
        diagnostic.genderOption();
        diagnostic.phoneNumber(" ");
        diagnostic.sendEmail(" ");
        diagnostic.clickContinueBtn();
        Log.info("validating diagnostic page booking details form");
        Log.info("Name error is displayed :"+diagnostic.sendErrorName());
        Assert.assertEquals(diagnostic.sendErrorName(),"Enter valid name","Name error message not displayed");
        Log.info("Mobile error is displayed :"+diagnostic.sendErrorMobile());
        Assert.assertEquals(diagnostic.sendErrorMobile(),"Invalid Phone Number","Phone error not displayed");
        Log.info("Date of birth error is displayed :"+diagnostic.sendErrorDob());
        Assert.assertEquals(diagnostic.sendErrorDob(),"Required","Dob error not displayed");
        Log.info("Email error is displayed :"+diagnostic.sendErrorEmail());
        Assert.assertEquals(diagnostic.sendErrorEmail(),"Required","Email error not displayed");
        Log.info("Test case 11 passed.");
    }
}
