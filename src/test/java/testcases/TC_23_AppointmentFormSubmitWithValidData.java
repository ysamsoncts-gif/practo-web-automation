package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.ExcelUtils;

import java.util.Map;

public class TC_23_AppointmentFormSubmitWithValidData extends BaseTest {

    @Test
    public void SubmitFormWithValidDate() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        String filePath ="C:\\Users\\2464425\\OneDrive - Cognizant\\Desktop\\AUTOMATION\\JAVA\\PROJECT\\MajorProject-Automation\\src\\test\\resources\\testData\\AllDetails.xlsx";
        Map<String, String> kv = ExcelUtils.readKeyValueSheet(filePath, "AppointmentFormValidData", false);
        String name = kv.getOrDefault("Name", "").trim();
        String contact = kv.getOrDefault("ContactNumber", "").trim();
        sp.fillTheForm(name,contact);
        String msg = sp.submitForm();
        if(msg.startsWith("Your")){
            Assert.assertEquals(msg,"Your OTP limit has been reached");
        }
        Assert.assertEquals(msg,"We have sent you an OTP on");
    }
}
