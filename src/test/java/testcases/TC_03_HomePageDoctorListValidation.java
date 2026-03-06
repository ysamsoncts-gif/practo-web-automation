package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import utilities.Log;

public class TC_03_HomePageDoctorListValidation extends BaseTest {

    @Test
    public void verifyNameOfDoctors() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.selectSearchDoctor("Doctor");
        home.selectSearchLocation("Bangalore");
        home.selectSubSearchLocation("JP Nagar");
        home.selectGender();
        home.selectFilterDoctorMale();
        home.selectDoctorReview();
        home.selectFilterPatientFeedback();
        home.selectExperience();
        home.selectFilter10Years();
        home.selectALlFilters();
        home.selectApolloCheckBox();
        Thread.sleep(2000);
        home.selectALlFilters();
        home.selectFeesRadio();
        Thread.sleep(2000);
        home.selectALlFilters();
        home.selectAvailabilityCheckBox();
        home.selectSortByFilter();
        home.selectExperienceFilter();
        Log.info("Test Case 03 started:");
        Log.info("List of doctors according to the applied filters is stored in excel");
        home.saveDoctorListToExcelFromList();
        Log.info("Test case 03 passed.");
        Assert.assertTrue(home.verifyDoctorLocation(),"Listed doctors are not located in JP Nagar");
    }
}
