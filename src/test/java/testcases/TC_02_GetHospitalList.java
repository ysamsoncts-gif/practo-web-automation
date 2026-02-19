package testcases;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_02_GetHospitalList extends BaseTest {

    @Test
    public void getNameOfHospital(){
        HomePage home = new HomePage(driver);
        home.searchLocation("Bangalore");
        home.searchHospitalClinic("Hospital");
    }
}
