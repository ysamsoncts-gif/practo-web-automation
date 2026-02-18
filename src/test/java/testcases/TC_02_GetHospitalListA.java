package testcases;


import basetest.BaseTest;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class TC_02_GetHospitalListA extends BaseTest {

    @Test
    public void getNameOfHospital(){
        HomePage home = new HomePage(driver);
        home.searchLocation("JP Nagar");
        home.searchHospitalClinic("Hospital");
        home.getHospitalName();
    }
}
