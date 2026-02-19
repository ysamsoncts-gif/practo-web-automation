package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_20_AppointmentFormFieldsValidation extends BaseTest {


    @Test
    public void test_FieldsPresent(){
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();

        Assert.assertTrue(sp.isFormCityDropdownVisible(),"City dropdown not visible");
        Assert.assertTrue(sp.isAilmentDropdownVisible(),"Aliment dropdown not visible");
        Assert.assertTrue(sp.isNameFieldVisible(),"Name field not visible");
        Assert.assertTrue(sp.isContactNumberVisible(),"Contact number field not visible");
        Assert.assertTrue(sp.isBookAppointmentButtonVisible(),"Book appointment button not visible");

    }
}
