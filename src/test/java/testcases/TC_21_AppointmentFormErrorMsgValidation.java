package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.Log;

public class TC_21_AppointmentFormErrorMsgValidation extends BaseTest {


    @Test
    public void VerifyErrorFieldsPresentOnEmptySubmit() {
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Log.info("Starting test case: test_MandatoryFieldErrorsOnEmptySubmit");
        sp.clickBookButton();
        Assert.assertEquals(sp.getNameError(), "Invalid User Name");
        Assert.assertEquals(sp.getContactError(), "Invalid Phone Number");
        Assert.assertEquals(sp.getAilmentError(), "Please pick a value");
        Log.info("Successfully validated error msg on empty form submission");
    }
}
