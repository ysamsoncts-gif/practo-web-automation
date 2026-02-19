package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_21_AppointmentFormErrorMsgValidation extends BaseTest {


    @Test
    public void test_MandatoryFieldErrorsOnEmptySubmit() {
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();

        sp.clickBookButton();
        Assert.assertEquals(sp.getNameError(), "Invalid User Name");
        Assert.assertEquals(sp.getContactError(), "Invalid Phone Number");
        Assert.assertEquals(sp.getAilmentError(), "Please pick a value");
    }
}
