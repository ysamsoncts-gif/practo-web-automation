package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import utilities.ExcelUtils;
import utilities.Log;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TC_14_DemoFormInvalidData extends BaseTest {

    @Test
    public void fillingInvalidData() throws Exception
    {
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToCorporateTab();
        cp.navigateToHealthWellnessPage();

        Log.info("Starting test case: DemoFormInvalidData");

        URL url = getClass().getClassLoader().getResource("testData/AllDetails.xlsx");
        if (url == null) {
            throw new IllegalStateException("Resource 'testData/AllDetails.xlsx' not found on classpath");
        }
        Path excelPath = Paths.get(url.toURI());   // Properly decodes spaces and Windows drive

        Map<String, String> kv = ExcelUtils.readKeyValueSheet(excelPath.toString(), "DemoFormInvalidData", false);
        String name = kv.getOrDefault("Name", "").trim();
        String organizationName = kv.getOrDefault("OrganizationName","").trim();
        String contact = kv.getOrDefault("ContactNumber", "").trim();
        String emailId = kv.getOrDefault("EmailId","").trim();
        cp.validatingData(name,organizationName,contact,emailId,3,2);
        Log.info("Data retrived from Excel file");
        Assert.assertFalse(cp.isSubmitBtnEnable(), "Submit button should not be enabled.");
        Log.info("Ending test case: DemoFormInvalidData");
    }
}
