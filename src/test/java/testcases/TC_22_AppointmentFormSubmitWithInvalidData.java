package testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.ExcelUtils;
import utilities.Log;

import java.io.File;
import java.util.Map;
public class TC_22_AppointmentFormSubmitWithInvalidData extends BaseTest {
    @Test
    public void SubmitFormWithInvalidData(){
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Log.info("Starting test case: SubmitFormWithInvalidData");
        String filePath = getClass().getClassLoader()
                .getResource("testData/AllDetails.xlsx")
                .getPath();
        Log.info("Started reading data from excel");
        Map<String, String> kv = ExcelUtils.readKeyValueSheet(filePath, "AppointmentFormInvalidData", false);
        String name = kv.getOrDefault("Name", "").trim();
        String contact = kv.getOrDefault("ContactNumber", "").trim();
        sp.fillTheForm(name,contact);
        Log.info("Successfully read data from excel");
        String nameErr = sp.getNameError();
        String contactErr = sp.getContactError();
        Assert.assertFalse(nameErr.isBlank(), "Name error should be shown for invalid name.");
        Assert.assertFalse(contactErr.isBlank(), "Contact error should be shown for invalid contact.");
        Log.info("test case: SubmitFormWithInvalidData completed successsfully");

    }
}
