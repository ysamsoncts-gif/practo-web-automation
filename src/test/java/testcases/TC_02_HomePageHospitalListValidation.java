package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import utilities.Log;

public class TC_02_HomePageHospitalListValidation extends BaseTest {

    @Test
    public void validateNameOfHospitals() {
        HomePage home = new HomePage(driver);

        home.selectSearchHospitalClinic("hospital");
        home.selectSearchLocation("Bangalore");
        home.selectSubSearchLocation("Jp Nagar");

        Log.info("Test Case 02 :");
        Log.info("List of hospitals in JP Nagar:");
        home.storeHospitalName();
        home.saveHospitalListToExcelFromList();
        Assert.assertTrue(home.verifyHospitalName(),"Listed hospitals are not located in JP Nagar");
    }
}
