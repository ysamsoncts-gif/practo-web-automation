package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;

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
        Thread.sleep(3000);
        home.selectALlFilters();
        home.selectFeesRadio();
        Thread.sleep(3000);
        home.selectALlFilters();
        home.selectAvailabilityCheckBox();

        home.selectSortByFilter();
        home.selectExperienceFilter();

        System.out.println("Test Case 03 :");
        System.out.println("List of doctors according to the applied filters :");
        home.storeDoctorName();
        home.saveDoctorListToExcelFromList();
        Assert.assertTrue(home.verifyDoctorLocation(),"Listed doctors are not located in JP Nagar");
    }
}
