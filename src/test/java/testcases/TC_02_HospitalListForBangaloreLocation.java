package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_02_HospitalListForBangaloreLocation extends BaseTest {

    @Test
    public void getNameOfHospital() {
        HomePage home = new HomePage(driver);
        home.selectSearchHospitalClinic("hospital");
        home.selectSearchLocation("Bangalore");
        home.selectSubSearchLocation("Jp Nagar");
        System.out.println("List of hospitals in JP Nagar:");
        home.storeHospitalName();
        Assert.assertTrue(home.verifyHospitalName(),"Listed hospitals are not located in JP Nagar");
    }
}
