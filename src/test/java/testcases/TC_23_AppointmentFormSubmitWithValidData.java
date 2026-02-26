package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.ExcelUtils;
import utilities.Log;

import java.util.Map;

public class TC_23_AppointmentFormSubmitWithValidData extends BaseTest {

    @Test
    public void SubmitFormWithValidDate() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Log.info("Starting test case: SubmitFormWithValidDate");
        String filePath = getClass().getClassLoader()
                .getResource("testData/AllDetails.xlsx")
                .getPath();
        Log.info("Started reading data from excel");
        Map<String, String> kv = ExcelUtils.readKeyValueSheet(filePath, "AppointmentFormValidData", false);
        String name = kv.getOrDefault("Name", "").trim();
        String contact = kv.getOrDefault("ContactNumber", "").trim();
        Log.info("Successfully read data from excel");
        Log.info("Starting filling form with extracted data");
        sp.fillTheForm(name,contact);
        String msg = sp.submitForm();
        Log.info("Form submitted with valid data");
        if(msg.startsWith("Your")){
            Assert.assertEquals(msg,"Your OTP limit has been reached");
        }
        Assert.assertEquals(msg,"We have sent you an OTP on");
        Log.info("Successfully validated form submition with valid data");

    }
}
