package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_22_AppointmentFormSubmitWithInvalidData extends BaseTest {
    @Test
    public void SubmitFormWithInvalidData(){
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        sp.fillTheForm("123is my name ","116276123");

        Assert.assertTrue(sp.isBookAppointmentButtonVisible(),"Form excepting invalid data");


    }
}
