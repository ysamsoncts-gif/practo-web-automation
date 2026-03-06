package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.Log;

public class TC_20_AppointmentFormFieldsValidation extends BaseTest {


    @Test
    public void VerifyMandatoryFieldsPresent(){
        SurgeriesPage sp = new SurgeriesPage(driver);
        Log.info("Starting test case: test_FieldsPresent");
        sp.navigateToSurgeriesPage();
        Assert.assertTrue(sp.isformCityDropdownArrowVisible(),"City dropdown not visible");
        Assert.assertTrue(sp.isAilmentDropdownVisible(),"Aliment dropdown not visible");
        Assert.assertTrue(sp.isNameFieldVisible(),"Name field not visible");
        Assert.assertTrue(sp.isContactNumberVisible(),"Contact number field not visible");
        Assert.assertTrue(sp.isBookAppointmentButtonVisible(),"Book appointment button not visible");
        Log.info("All test Fields Present");
    }
}
