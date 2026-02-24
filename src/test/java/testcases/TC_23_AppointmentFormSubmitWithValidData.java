package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_23_AppointmentFormSubmitWithValidData extends BaseTest {

    @Test
    public void SubmitFormWithValidDate() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        sp.fillTheForm("I am patient","9999199991");
        String msg = sp.submitForm();
        if(msg.startsWith("Your")){
            Assert.assertEquals(msg,"Your OTP limit has been reached");
        }
        Assert.assertEquals(msg,"We have sent you an OTP on");
    }
}
