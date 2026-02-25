package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.ExcelUtils;
import utilities.Log;

import java.util.Map;

public class TC_22_AppointmentFormSubmitWithInvalidData extends BaseTest {
    @Test
    public void SubmitFormWithInvalidData(){
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();

        Log.info("Starting test case: SubmitFormWithInvalidData");

        String filePath ="C:\\Users\\2464425\\OneDrive - Cognizant\\Desktop\\AUTOMATION\\JAVA\\PROJECT\\MajorProject-Automation\\src\\test\\resources\\testData\\AllDetails.xlsx";
        Map<String, String> kv = ExcelUtils.readKeyValueSheet(filePath, "AppointmentFormInvalidData", false);
        String name = kv.getOrDefault("Name", "").trim();
        String contact = kv.getOrDefault("ContactNumber", "").trim();
        sp.fillTheForm(name,contact);

        String nameErr = sp.getNameError();
        String contactErr = sp.getContactError();

        Assert.assertFalse(nameErr.isBlank(), "Name error should be shown for invalid name.");
        Assert.assertFalse(contactErr.isBlank(), "Contact error should be shown for invalid contact.");


    }
}
